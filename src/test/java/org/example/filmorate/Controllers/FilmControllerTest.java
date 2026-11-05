package org.example.filmorate.Controllers;

import org.example.filmorate.exceptions.ValidationException;
import org.example.filmorate.models.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private FilmController filmController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController();
    }

    @Test
    void shouldThrowExceptionWhenFilmNameIsBlank() {
        Film film = new Film();
        film.setName("");
        film.setDescription("Хороший фильм");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        assertThrows(ValidationException.class, () -> filmController.createdFilm(film));
    }

    @Test
    void shouldThrowExceptionWhenDurationIsNegative() {
        Film film = new Film();
        film.setName("Матрица");
        film.setDescription("Описание");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(-10);

        assertThrows(ValidationException.class, () -> filmController.createdFilm(film));
    }

    @Test
    void shouldHandleNullRequest() {
        assertThrows(ValidationException.class, () -> filmController.createdFilm(null));
    }
}