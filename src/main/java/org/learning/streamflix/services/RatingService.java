package org.learning.streamflix.services;

import org.learning.streamflix.controllers.entities.Rating;
import org.learning.streamflix.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  // Add a rating to a movie
  public Rating addRating(Long movieId, Long userId, Rating rating) {
    rating.setMovieId(movieId);
    rating.setUserId(userId);
    return ratingRepository.save(rating);
  }

  // Get ratings for a movie
  public List<Rating> getRatingsByMovie(Long movieId) {
    return ratingRepository.findByMovieId(movieId);
  }

  // Get ratings made by a user
  public List<Rating> getRatingsByUser(Long userId) {
    return ratingRepository.findByUserId(userId);
  }

  // Delete a user's own rating
  public void deleteRating(Long ratingId, Long userId) {
    Rating rating = ratingRepository.findById(ratingId)
        .orElseThrow(() -> new RuntimeException("Rating not found"));
    if (!rating.getUserId().equals(userId)) {
      throw new RuntimeException("Unauthorized to delete this rating");
    }
    ratingRepository.deleteById(ratingId);
  }
}