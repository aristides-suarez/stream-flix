package org.learning.streamflix.services;

import org.learning.streamflix.controllers.entities.User;
import org.learning.streamflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Register a new user
  public User registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRegistrationDate(java.sql.Date.valueOf(LocalDate.now()));
    user.setRole(User.Role.USER);
    return userRepository.save(user);
  }

  // Authenticate user (login)
  public String authenticateUser(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
    if (passwordEncoder.matches(password, user.getPassword())) {
      return "Authentication successful"; // Replace with token generation logic
    } else {
      throw new RuntimeException("Invalid credentials");
    }
  }

  // Get user profile
  public User getUserProfile(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  // Update user profile
  public User updateUserProfile(Long userId, User updatedUser) {
    User existingUser = getUserProfile(userId);
    existingUser.setUsername(updatedUser.getUsername());
    existingUser.setEmail(updatedUser.getEmail());
    return userRepository.save(existingUser);
  }

  // Change password
  public void changePassword(Long userId, String newPassword) {
    User user = getUserProfile(userId);
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }

  // Update user role (only administrators)
  public User updateUserRole(Long userId, User.Role newRole) {
    User user = getUserProfile(userId);
    user.setRole(newRole);
    return userRepository.save(user);
  }
}