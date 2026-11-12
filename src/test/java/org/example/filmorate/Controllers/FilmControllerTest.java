package org.example.filmorate.Controllers;

import org.example.filmorate.exceptions.NotFoundException;
import org.example.filmorate.exceptions.ValidationException;
import org.example.filmorate.models.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private FilmController filmController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController();
    }

    @Test
    void shouldCreateFilmSuccessfullyAndAssignId1() {
        Film film = new Film();
        film.setName("Интерстеллар");
        film.setDescription("Хороший фильм");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        Film created = filmController.createdFilm(film);

        assertNotNull(created);
        assertEquals(1, created.getId());

        List<Film> films = filmController.allFilms();
        assertEquals(1, films.size());
        assertEquals(created, films.get(0));
    }

    @Test
    void shouldUpdateFilmSuccessfully() {
        Film film = new Film();
        film.setName("Интерстеллар");
        film.setDescription("Хороший фильм");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);
        Film created = filmController.createdFilm(film);

        Film updatedFilm = new Film();
        updatedFilm.setId(created.getId());
        updatedFilm.setName("Интерстеллар Обновленный");
        updatedFilm.setDescription("Хороший фильм");
        updatedFilm.setReleaseDate(LocalDate.of(2000, 1, 1));
        updatedFilm.setDuration(150);
        filmController.updateFilm(updatedFilm);

        Film stored = filmController.allFilms().get(0);
        assertEquals("Интерстеллар Обновленный", stored.getName());
        assertEquals(150, stored.getDuration());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingNonExistentFilm() {
        Film film = new Film();
        film.setId(999);
        film.setName("Фильм");
        film.setDescription("Описание");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        assertThrows(NotFoundException.class, () -> filmController.updateFilm(film));
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
}
