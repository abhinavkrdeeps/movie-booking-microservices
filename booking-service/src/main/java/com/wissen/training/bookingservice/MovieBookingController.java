package com.wissen.training.bookingservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/movies/book/{id}")
	public ResponseEntity<BookingInfo> bookMovieTicket(@PathVariable Integer id) {
		Map<String,Integer> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
	    ResponseEntity<Movie> responseEntity  = restTemplate.getForEntity("http://localhost:8765/MOVIE-CATALOGUE-SERVICE/movies/{id}", Movie.class,uriVariables);
	    
	    
//	    InstanceInfo info = eurekaClient.getApplication("eureka-naming-servers").getInstances().get(0);
//	    System.out.println("app name: "+info.getAppName());
	    
	    // Call to Movie-catalogue service to fetch movie with a given id
	    Movie movie1 = wBuilder
	    		.build()
	    		.get()
	    		.uri("http://localhost:8089/movies/{id}", uriVariables)
	    		.retrieve()
	    		.bodyToMono(Movie.class)
	    		.block();
	    
	    Movie movie = responseEntity.getBody();
	    
	    // Call payment-service for performing payments and then book tickets
	    String paymentServiceUri="http://localhost:8093/movies/book/{userId}/{price}/payment";
	    Map<String,String> paymentServiceUriVariables = new HashMap<>();
	    paymentServiceUriVariables.put("userId","123");
	    paymentServiceUriVariables.put("price", movie.getPrice().toString());
	    PaymentSheet paymentSheet = wBuilder
	    		.build()
	    		.get()
	    		.uri(paymentServiceUri, paymentServiceUriVariables)
	    		.retrieve()
	    		.bodyToMono(PaymentSheet.class)
	    		.block();
	    if(paymentSheet !=null && paymentSheet.isSuccess()) {
	    BookingInfo bookingInfo = new BookingInfo(1,movie1.getMovieId(),movie1.getPrice(),1);
		
	    return new ResponseEntity<BookingInfo>(bookingInfo, HttpStatus.CREATED);
	    }else {
	    
	    	return new ResponseEntity<BookingInfo>(new BookingInfo(),HttpStatus.BAD_GATEWAY);
	    }
	}

}
