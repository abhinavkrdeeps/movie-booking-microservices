package com.wissen.training.bookingservice;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class MovieBookingController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder wBuilder;

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    @Lazy
    private PropertyConfig propertyConfig;

    private Logger logger = LoggerFactory.getLogger(MovieBookingController.class);

    @GetMapping("/")
    public String getHome() {
        return "Hello World";
    }

    /**
     * The method bookMovieTicket respond to api call of /movies/book/{id} .
     * First This will call movie-catalogue-service to fetch information about the particular.
     * After this it will call payment-service which will return information about the payment status.
     * if status of the payment is success. Booking Info is returned with Http Status of 201 (Created) else
     * if status of the payment is failed. An Empty Booking Info is returned with Http Status of BAD_GATEWAY(502)
     * @param id  (Movie Id)
     * @return
     */
    @GetMapping("/movies/book/{id}")
    //@Retry(name = "book-movie", fallbackMethod = "fallbackResponseForBookMovieTicket")
    @RateLimiter(name = "default") // for every 10sec allow only 10000 api calls to this particular service.
    @CircuitBreaker(name = "booking-service", fallbackMethod = "fallbackResponseForBookMovieTicket")
    public ResponseEntity<BookingInfo> bookMovieTicket(@PathVariable Integer id) {
        logger.info("Api Called");
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        ResponseEntity<Movie> responseEntity =
                restTemplate.getForEntity(
                        propertyConfig.getMovieCatalogueServiceUri() + "/fails", Movie.class, uriVariables);


        // Call to Movie-catalogue service to fetch movie with a given id
        Movie movie1 =
                wBuilder
                        .build()
                        .get()
                        .uri(propertyConfig.getMovieCatalogueServiceUri(), uriVariables)
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();

        Movie movie = responseEntity.getBody();

        // Call payment-service for performing payments and then book tickets;
        Map<String, String> paymentServiceUriVariables = new HashMap<>();
        paymentServiceUriVariables.put("userId", "123");
        paymentServiceUriVariables.put("price", movie.getPrice().toString());
        PaymentSheet paymentSheet =
                wBuilder
                        .build()
                        .get()
                        .uri(propertyConfig.getPaymentServiceUri(), paymentServiceUriVariables)
                        .retrieve()
                        .bodyToMono(PaymentSheet.class)
                        .block();

        // Creating booking info based on if payment got succeeded or not
        if (paymentSheet != null && paymentSheet.isSuccess()) {
            BookingInfo bookingInfo = new BookingInfo(1, movie1.getMovieId(), movie1.getPrice(), 1);

            return new ResponseEntity<BookingInfo>(bookingInfo, HttpStatus.CREATED);
        } else {

            return new ResponseEntity<BookingInfo>(new BookingInfo(), HttpStatus.BAD_GATEWAY);
        }
    }

    /**
     * The method fallbackResponseForBookMovieTicket is a fallback method.
     * It is called when the movie-catalog-service or payment service is down.
     *
     * @param id
     * @param exception (A Throwable Type)
     * @return
     */
    public ResponseEntity<BookingInfo> fallbackResponseForBookMovieTicket(Integer id, Exception exception) {
        BookingInfo defaultBookingInfo = new BookingInfo();
        logger.info("Fallback Response Active");
        return new ResponseEntity<>(defaultBookingInfo, HttpStatus.OK);

    }
}
