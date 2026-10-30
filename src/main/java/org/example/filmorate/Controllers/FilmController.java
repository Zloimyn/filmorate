package org.example.filmorate.Controllers;

import org.example.filmorate.models.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class FilmController {
    private int id= 0;
    private final HashMap<Integer, Film> filmHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    @GetMapping("/allfilms")
    public List<Film> allFilms(){
        ArrayList<Film> list = new ArrayList<>(filmHashMap.values());
        return list;
    }

    @PostMapping("/addfilm")
    public Film createdFilm(@RequestBody Film film) {
        validateFilm(film);

        id++;
        film.setId(id);
        log.debug("Добавление фильма: id={}, name={}", film.getId(), film.getName());
        filmHashMap.put(id, film);
        return film;
    }

    @PutMapping("/updatefilm")
    public Film updateFilm(@RequestBody Film film){
        if (film == null || film.getId() == null || !filmHashMap.containsKey(film.getId())) {
            throw new RuntimeException("Ошибка");
        }

        validateFilm(film);

        log.debug("Добавление фильма: id={}, name={}", film.getId(), film.getName());
        filmHashMap.put(film.getId(), film);
        return film;
    }

    private void validateFilm(Film film) {
        if (film == null || film.getName() == null || film.getName().isBlank()
            || film.getReleaseDate() == null
            || film.getReleaseDate().isBefore(MIN_RELEASE_DATE)
            || film.getDurations() <= 0) {
            throw new RuntimeException("Ошибка");
        }
    }
}
