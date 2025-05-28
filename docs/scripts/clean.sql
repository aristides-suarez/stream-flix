-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Truncate tables in the correct order to avoid foreign key issues
TRUNCATE TABLE Rating;
TRUNCATE TABLE movie_genres;
TRUNCATE TABLE Movie;
TRUNCATE TABLE Genre;
TRUNCATE TABLE User;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;