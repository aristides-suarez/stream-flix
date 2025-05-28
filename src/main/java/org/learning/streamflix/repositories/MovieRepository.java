package org.learning.streamflix.repositories;

import org.learning.streamflix.controllers.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

  List<Movie> findByTitleContainingOrGenresContainingOrReleaseYear(String title, String genre, Integer year);

  List<Movie> findByGenresContainingAndReleaseYearAndAgeRating(String genre, Integer year, String ageRating);
}