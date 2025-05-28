package org.learning.streamflix.controllers.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Genre name is required")
  @Size(max = 50, message = "Genre name must not exceed 50 characters")
  @Column(nullable = false, unique = true)
  private String name;
}