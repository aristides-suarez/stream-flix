package org.learning.streamflix.controllers;

import org.learning.streamflix.controllers.entities.Rating;
import org.learning.streamflix.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

  @Autowired
  private RatingService ratingService;

  // Añadir valoración a una película
  @PostMapping("/add")
  public ResponseEntity<Rating> addRating(@RequestParam Long movieId, @RequestParam Long userId, @RequestBody Rating rating) {
    Rating addedRating = ratingService.addRating(movieId, userId, rating);
    return ResponseEntity.ok(addedRating);
  }

  // Obtener valoraciones de una película
  @GetMapping("/movie/{movieId}")
  public ResponseEntity<List<Rating>> getRatingsByMovie(@PathVariable Long movieId) {
    List<Rating> ratings = ratingService.getRatingsByMovie(movieId);
    return ResponseEntity.ok(ratings);
  }

  // Obtener valoraciones realizadas por un usuario
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable Long userId) {
    List<Rating> ratings = ratingService.getRatingsByUser(userId);
    return ResponseEntity.ok(ratings);
  }

  // Eliminar valoración propia
  @DeleteMapping("/delete/{ratingId}")
  public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId, @RequestParam Long userId) {
    ratingService.deleteRating(ratingId, userId);
    return ResponseEntity.noContent().build();
  }
}