package org.learning.streamflix.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for administrative operations.
 * Provides endpoints accessible only to users with administrator privileges.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

  /**
   * Retrieves the admin dashboard.
   * This endpoint is restricted to users with the 'ADMINISTRATOR' role.
   *
   * @return A welcome message for the admin dashboard.
   */
  @GetMapping("/dashboard")
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public String getAdminDashboard() {
    return "Welcome to the Admin Dashboard!";
  }
}