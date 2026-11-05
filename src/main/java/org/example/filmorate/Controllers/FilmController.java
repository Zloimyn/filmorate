package org.example.filmorate.Controllers;

import org.example.filmorate.exceptions.NotFoundException;
import org.example.filmorate.exceptions.ValidationException;
import org.example.filmorate.models.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private int id = 0;
    private final HashMap<Integer, Film> filmHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    private static final int MAX_DESCRIPTION_LENGTH = 200;

    @GetMapping
    public List<Film> allFilms() {
        return new ArrayList<>(filmHashMap.values());
    }

    @PostMapping
    public Film createdFilm(@RequestBody(required = false) Film film) {
        if (film == null) {
            log.error("Ошибка: передан пустой запрос на добавление фильма");
            throw new ValidationException("Запрос не может быть пустым");
        }
        validateFilm(film);

        id++;
        film.setId(id);
        log.info("Добавление фильма: id={}, name={}", film.getId(), film.getName());
        filmHashMap.put(id, film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody(required = false) Film film) {
        if (film == null) {
            log.error("Ошибка при обновлении фильма: пустой запрос");
            throw new ValidationException("Запрос не может быть пустым");
        }
        if (film.getId() == null || !filmHashMap.containsKey(film.getId())) {
            log.error("Ошибка при обновлении фильма: фильм с id={} не найден", film.getId());
            throw new NotFoundException("Фильм с таким id не найден");
        }

        validateFilm(film);

        log.info("Обновление фильма: id={}, name={}", film.getId(), film.getName());
        filmHashMap.put(film.getId(), film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.error("Ошибка: название фильма пустое");
            throw new ValidationException("Название фильма не может быть пустым");
        }
        if (film.getDescription() != null && film.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            log.error("Ошибка: длина описания превышает 200 символов");
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate() == null || film.getReleaseDate().isBefore(MIN_RELEASE_DATE)) {
            log.error("Ошибка даты релиза: {}", film.getReleaseDate());
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        if (film.getDuration() <= 0) {
            log.error("Ошибка продолжительности: {}", film.getDuration());
            throw new ValidationException("Продолжительность фильма должна быть положительной");
        }
    }
}