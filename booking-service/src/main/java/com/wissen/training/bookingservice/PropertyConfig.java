package com.wissen.training.bookingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "booking-service")
public class PropertyConfig {


    private String movieCatalogueServiceUri;


    private String paymentServiceUri;

    public PropertyConfig() {
    }

    public PropertyConfig(String movieCatalogueServiceUri, String paymentServiceUri) {
        this.movieCatalogueServiceUri = movieCatalogueServiceUri;
        this.paymentServiceUri = paymentServiceUri;
    }

    public String getMovieCatalogueServiceUri() {
        return movieCatalogueServiceUri;
    }

    public void setMovieCatalogueServiceUri(String movieCatalogueServiceUri) {
        this.movieCatalogueServiceUri = movieCatalogueServiceUri;
    }

    public String getPaymentServiceUri() {
        return paymentServiceUri;
    }

    public void setPaymentServiceUri(String paymentServiceUri) {
        this.paymentServiceUri = paymentServiceUri;
    }
}
