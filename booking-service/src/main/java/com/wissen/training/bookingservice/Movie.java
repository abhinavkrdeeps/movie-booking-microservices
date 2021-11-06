package com.wissen.training.bookingservice;

public class Movie {
    private Integer movieId;
    private String movieName;
    private Double price;

    public Movie() {
    }

    public Movie(Integer movieId, String movieName, Double price) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.price = price;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", price=" + price +
                '}';
    }
}
