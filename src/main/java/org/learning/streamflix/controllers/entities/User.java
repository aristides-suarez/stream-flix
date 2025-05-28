package org.learning.streamflix.controllers.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Username is required")
  private String username;

  @Email(message = "Email must be valid")
  @NotBlank(message = "Email is required")
  @Column(unique = true, nullable = false)
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  @Column(nullable = false)
  private String encryptedPassword;

  @Enumerated(EnumType.STRING)
  private Role role;

  private LocalDate registrationDate;

  private boolean active;

  public enum Role {
    USER,
    ADMINISTRATOR
  }
}