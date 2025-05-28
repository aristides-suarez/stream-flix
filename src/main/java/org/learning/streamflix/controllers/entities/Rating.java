package org.learning.streamflix.controllers.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @DecimalMin(value = "0", message = "Rating must be at least 0")
  @DecimalMax(value = "10", message = "Rating must not exceed 10")
  private int score;

//  @NotBlank(message = "Review is required")
//  @Size(max = 500, message = "Review must not exceed 500 characters")
//  private String review;

  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}