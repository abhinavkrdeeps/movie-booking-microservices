package com.wissen.training.bookingservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTests {

    @Autowired
    private MovieBookingController movieBookingController;

    @Test
    public void testMovieBookingController(){
        assert movieBookingController !=null;
    }


}
