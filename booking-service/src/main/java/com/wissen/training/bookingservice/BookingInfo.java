package com.wissen.training.bookingservice;

public class BookingInfo {
	private Integer bookingId;
	private Integer userId;
	private Double price;
	private Integer movieId;
	
	
	
	public BookingInfo() {
		// TODO Auto-generated constructor stub
	}
	public BookingInfo(Integer bookingId, Integer userId, Double price, Integer movieId) {
		this.bookingId = bookingId;
		this.userId = userId;
		this.price = price;
		this.movieId = movieId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "BookingInfo [bookingId=" + bookingId + ", userId=" + userId + ", price=" + price + ", movieId="
				+ movieId + "]";
	}
	
	
	

}
