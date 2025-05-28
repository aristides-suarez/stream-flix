-- Insert sample data into the User table
INSERT INTO User (username, email, encrypted_password, role, registration_date, active) VALUES
('john_doe', 'john.doe@example.com', '$2a$10$abcdefghijklmnop', 'USER', CURDATE(), true),
('jane_admin', 'jane.admin@example.com', '$2a$10$qrstuvwxyz123456', 'ADMINISTRATOR', CURDATE(), true),
('peter_user', 'peter.user@example.com', '$2a$10$7890abcdefghijklmn', 'USER', CURDATE(), true);

-- Insert sample data into the Genre table
INSERT INTO Genre (name) VALUES
('Action'),
('Comedy'),
('Drama'),
('Sci-Fi');

-- Insert sample data into the Movie table
INSERT INTO Movie (title, description, release_year, director, duration, age_rating, cover_image_url, average_rating, added_date) VALUES
('Movie 1', 'Description for Movie 1', 2020, 'Director 1', 120, 'PG-13', 'http://example.com/movie1.jpg', 7.5, CURDATE()),
('Movie 2', 'Description for Movie 2', 2021, 'Director 2', 110, 'R', 'http://example.com/movie2.jpg', 8.0, CURDATE()),
('Movie 3', 'Description for Movie 3', 2022, 'Director 3', 130, 'PG', 'http://example.com/movie3.jpg', 6.5, CURDATE());

-- Insert sample data into the movie_genres table
INSERT INTO movie_genres (movie_id, genre_id) VALUES
(1, 1), -- Movie 1 is Action
(1, 3), -- Movie 1 is Drama
(2, 2), -- Movie 2 is Comedy
(3, 4); -- Movie 3 is Sci-Fi

-- Insert sample data into the Rating table
INSERT INTO Rating (score, review, movie_id, user_id) VALUES
(8.0, 'Great movie!', 1, 1),
(9.0, 'Excellent acting', 1, 2),
(7.0, 'Good storyline', 2, 3);