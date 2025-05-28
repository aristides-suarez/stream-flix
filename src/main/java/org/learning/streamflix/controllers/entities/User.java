package org.learning.streamflix.controllers.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Username is required")
  private String username;

  @Email(message = "Email must be valid")
  @NotBlank(message = "Email is required")
  @Column(unique = true, nullable = false)
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  private Date registrationDate;

//  private boolean active;

  public enum Role {
    USER,
    ADMINISTRATOR
  }
}