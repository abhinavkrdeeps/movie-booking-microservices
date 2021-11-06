package com.wissen.training.bookingservice;

public class PaymentSheet {
	
	private Integer paymentId;
	private Integer userId;
	private String paymentMethod;
	private Double price;
	private boolean isSuccess;
	
	
	
	public PaymentSheet() {
		
	}
	
	public PaymentSheet(Integer paymentId, Integer userId, String paymentMethod, Double price, boolean isSuccess) {
		this.paymentId = paymentId;
		this.userId = userId;
		this.paymentMethod = paymentMethod;
		this.price = price;
		this.isSuccess = isSuccess;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "PaymentSheet [paymentId=" + paymentId + ", userId=" + userId + ", paymentMethod=" + paymentMethod
				+ ", price=" + price + ", isSuccess=" + isSuccess + "]";
	}
	
	

}
