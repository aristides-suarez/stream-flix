package org.learning.streamflix.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(roles = "ADMINISTRATOR")
  void testGetAdminDashboardWithAdminRole() throws Exception {
    mockMvc.perform(get("/admin/dashboard"))
        .andExpect(status().isOk())
        .andExpect(content().string("Welcome to the Admin Dashboard!"));
  }

  @Test
  void testGetAdminDashboardWithoutAuthentication() throws Exception {
    mockMvc.perform(get("/admin/dashboard"))
        .andExpect(status().isForbidden());
  }
}