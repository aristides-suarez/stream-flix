package org.learning.streamflix.controllers.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Title is required")
  private String title;

  @NotBlank(message = "Description is required")
  @Size(max = 1000, message = "Description must not exceed 1000 characters")
  @Column(length = 1000)
  private String description;

  @Min(value = 1888, message = "Release year must be valid")
  private int releaseYear;

  @NotBlank(message = "Director is required")
  private String director;

  @ManyToMany
  @JoinTable(
    name = "movie_genres",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private List<Genre> genres;

  @Min(value = 1, message = "Duration must be at least 1 minute")
  private int duration;

  @NotBlank(message = "Age rating is required")
  private String ageRating;

  @NotBlank(message = "Cover image URL is required")
  private String coverImageUrl;

  @DecimalMin(value = "0.0", message = "Average rating must be at least 0.0")
  @DecimalMax(value = "10.0", message = "Average rating must not exceed 10.0")
  private double averageRating;

  private LocalDate addedDate;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Rating> ratings;
}