package org.learning.streamflix.controllers;

import org.junit.jupiter.api.Test;
import org.learning.streamflix.controllers.entities.Movie;
import org.learning.streamflix.services.MovieService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MovieService movieService;

  @Test
  void testGetMovies() throws Exception {
    mockMvc.perform(get("/movies"))
        .andExpect(status().isOk());
  }

  @Test
  void testCreateMovieWithAdminRole() throws Exception {
    Movie movie = new Movie();
    movie.setTitle("Example Movie");
    when(movieService.createMovie(any(Movie.class))).thenReturn(movie);

    mockMvc.perform(post("/movies")
        .contentType(APPLICATION_JSON)
        .content("{\"title\":\"Example Movie\"}")
        .with(user("admin").roles("ADMINISTRATOR")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Example Movie"));
  }

  @Test
  void testCreateMovieWithoutAdminRole() throws Exception {
    mockMvc.perform(post("/movies")
        .contentType(APPLICATION_JSON)
        .content("{\"title\":\"Example Movie\"}"))
        .andExpect(status().isForbidden());
  }

  @Test
  void testSearchMovies() throws Exception {
    mockMvc.perform(get("/movies/search?title=Example"))
        .andExpect(status().isOk());
  }
}