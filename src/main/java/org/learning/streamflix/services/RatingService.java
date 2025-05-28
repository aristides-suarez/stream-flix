package org.learning.streamflix.services;

import org.learning.streamflix.controllers.entities.Movie;
import org.learning.streamflix.controllers.entities.Rating;
import org.learning.streamflix.controllers.entities.User;
import org.learning.streamflix.repositories.MovieRepository;
import org.learning.streamflix.repositories.RatingRepository;
import org.learning.streamflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;
  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private UserRepository userRepository;

  // Add a rating to a movie
  public Rating addRating(Long movieId, Long userId, Rating rating) {
    Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new RuntimeException("Movie not found"));
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    rating.setMovie(movie);
    rating.setUser(user);
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
    if (!rating.getUser().getId().equals(userId)) {
      throw new RuntimeException("Unauthorized to delete this rating");
    }
    ratingRepository.deleteById(ratingId);
  }
}