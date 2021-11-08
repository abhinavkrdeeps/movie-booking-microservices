package com.wissen.training.moviecatalogueservice;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/movies")
public class MovieResource {


    @GetMapping
    public List<Movie> getAllMovies(){
       List<Movie> movies = new ArrayList<>();
       movies.add(new Movie(1,"Sherlock", 100.0));
       movies.add(new Movie(1,"Dybukk", 500.0));
       
       return movies;
    }
    
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") Integer movieId) {
    	
    	return new Movie(movieId,"Random Name",500.0);
    }
}
