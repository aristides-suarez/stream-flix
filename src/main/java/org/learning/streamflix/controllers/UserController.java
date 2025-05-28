package org.learning.streamflix.controllers;

import org.learning.streamflix.controllers.entities.User;
import org.learning.streamflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  // Registro de nuevos usuarios
  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    User registeredUser = userService.registerUser(user);
    return ResponseEntity.ok(registeredUser);
  }

  // Autenticación de usuarios (login)
  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
    String token = userService.authenticateUser(email, password);
    return ResponseEntity.ok(token);
  }

  // Obtener perfil de usuario
  @GetMapping("/profile")
  public ResponseEntity<User> getUserProfile(@RequestParam Long userId) {
    User userProfile = userService.getUserProfile(userId);
    return ResponseEntity.ok(userProfile);
  }

  // Actualizar información de perfil
  @PutMapping("/profile")
  public ResponseEntity<User> updateUserProfile(@RequestParam Long userId, @RequestBody User user) {
    User updatedUser = userService.updateUserProfile(userId, user);
    return ResponseEntity.ok(updatedUser);
  }

  // Cambiar contraseña
  @PutMapping("/change-password")
  public ResponseEntity<Void> changePassword(@RequestParam Long userId, @RequestParam String newPassword) {
    userService.changePassword(userId, newPassword);
    return ResponseEntity.noContent().build();
  }

  // Gestión de roles (solo administradores)
  @Secured("ROLE_ADMINISTRATOR")
  @PutMapping("/roles")
  public ResponseEntity<User> updateUserRole(@RequestParam Long userId, @RequestParam User.Role newRole) {
    User updatedUser = userService.updateUserRole(userId, newRole);
    return ResponseEntity.ok(updatedUser);
  }
}