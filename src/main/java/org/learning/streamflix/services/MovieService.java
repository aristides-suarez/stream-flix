package org.learning.streamflix.services;

import org.learning.streamflix.controllers.entities.Movie;
import org.learning.streamflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  // Create a new movie
  public Movie createMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  // Get paginated list of movies
  public Page<Movie> getMovies(Pageable pageable) {
    return movieRepository.findAll(pageable);
  }

  // Get details of a specific movie
  public Movie getMovieById(Long id) {
    return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
  }

  // Update an existing movie
  public Movie updateMovie(Long id, Movie movie) {
    Movie existingMovie = getMovieById(id);
    existingMovie.setTitle(movie.getTitle());
    existingMovie.setDescription(movie.getDescription());
    existingMovie.setReleaseYear(movie.getReleaseYear());
    existingMovie.setDirector(movie.getDirector());
    existingMovie.setGenres(movie.getGenres());
    existingMovie.setDuration(movie.getDuration());
    existingMovie.setAgeRating(movie.getAgeRating());
    existingMovie.setCoverImageUrl(movie.getCoverImageUrl());
//    existingMovie.setAverageRating(movie.getAverageRating());
    existingMovie.setAddedDate(movie.getAddedDate());
    return movieRepository.save(existingMovie);
  }

  // Delete a movie
  public void deleteMovie(Long id) {
    movieRepository.deleteById(id);
  }

  // Search movies by title, genre, or year
  public List<Movie> searchMovies(String title, String genre, Integer year) {
    return movieRepository.findByTitleContainingOrGenresContainingOrReleaseYear(title, genre, year);
  }

  // Filter movies by genre, year, or age rating
  public List<Movie> filterMovies(String genre, Integer year, String ageRating) {
    return movieRepository.findByGenresContainingAndReleaseYearAndAgeRating(genre, year, ageRating);
  }
}