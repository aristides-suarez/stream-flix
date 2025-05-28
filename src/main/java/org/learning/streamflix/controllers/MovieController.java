package org.learning.streamflix.controllers;

import org.learning.streamflix.controllers.entities.Movie;
import org.learning.streamflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing movies in the Streamflix application.
 * Provides endpoints for CRUD operations and movie searches.
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  /**
   * Creates a new movie.
   * This endpoint is restricted to administrators.
   *
   * @param movie The movie details to be created.
   * @return The created movie.
   */
  @Secured("ROLE_ADMINISTRATOR")
  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    Movie createdMovie = movieService.createMovie(movie);
    return ResponseEntity.ok(createdMovie);
  }

  /**
   * Retrieves a paginated list of movies.
   *
   * @param pageable Pagination details.
   * @return A page of movies.
   */
  @GetMapping
  public ResponseEntity<Page<Movie>> getMovies(Pageable pageable) {
    Page<Movie> movies = movieService.getMovies(pageable);
    return ResponseEntity.ok(movies);
  }

  /**
   * Retrieves the details of a specific movie by its ID.
   *
   * @param id The ID of the movie.
   * @return The movie details.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    Movie movie = movieService.getMovieById(id);
    return ResponseEntity.ok(movie);
  }

  /**
   * Updates an existing movie.
   * This endpoint is restricted to administrators.
   *
   * @param id The ID of the movie to update.
   * @param movie The updated movie details.
   * @return The updated movie.
   */
  @Secured("ROLE_ADMINISTRATOR")
  @PutMapping("/{id}")
  public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    Movie updatedMovie = movieService.updateMovie(id, movie);
    return ResponseEntity.ok(updatedMovie);
  }

  /**
   * Deletes a movie by its ID.
   * This endpoint is restricted to administrators.
   *
   * @param id The ID of the movie to delete.
   * @return A response indicating the movie was deleted.
   */
  @Secured("ROLE_ADMINISTRATOR")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Searches for movies by title, genre, or year.
   *
   * @param title The title of the movie (optional).
   * @param genre The genre of the movie (optional).
   * @param year The release year of the movie (optional).
   * @return A list of movies matching the search criteria.
   */
  @GetMapping("/search")
  public ResponseEntity<List<Movie>> searchMovies(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String genre,
      @RequestParam(required = false) Integer year) {
    List<Movie> movies = movieService.searchMovies(title, genre, year);
    return ResponseEntity.ok(movies);
  }

  /**
   * Filters movies by genre, year, or age rating.
   *
   * @param genre The genre of the movie (optional).
   * @param year The release year of the movie (optional).
   * @param ageRating The age rating of the movie (optional).
   * @return A list of movies matching the filter criteria.
   */
  @GetMapping("/filter")
  public ResponseEntity<List<Movie>> filterMovies(
      @RequestParam(required = false) String genre,
      @RequestParam(required = false) Integer year,
      @RequestParam(required = false) String ageRating) {
    List<Movie> movies = movieService.filterMovies(genre, year, ageRating);
    return ResponseEntity.ok(movies);
  }
}