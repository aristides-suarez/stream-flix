package org.learning.streamflix.repositories;

import org.learning.streamflix.controllers.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

  List<Rating> findByMovieId(Long movieId);

  List<Rating> findByUserId(Long userId);
}