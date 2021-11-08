package com.wissen.training.paymentservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentResource {

	@GetMapping("/")
	public String getHomePage(){
		return "Hello World";
	}

	@GetMapping("/movies/book/{userId}/{price}/payment")
	public PaymentSheet getPaymentGateway(@PathVariable String userId, @PathVariable String price) {

		return new PaymentSheet(1,Integer.parseInt(userId),"UPI",Double.valueOf(price),true);
	}

}
