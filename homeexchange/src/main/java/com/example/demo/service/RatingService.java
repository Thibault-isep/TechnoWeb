package com.example.demo.service;

import com.example.demo.entities.Rating;
import com.example.demo.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getRatings() {return ratingRepository.findAll();}
}
