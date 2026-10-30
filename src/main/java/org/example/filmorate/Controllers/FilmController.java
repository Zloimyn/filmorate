package org.example.filmorate.Controllers;

import org.example.filmorate.models.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class FilmController {
    private final HashMap<Integer, Film> filmHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @GetMapping("/allfilms")
    public List<Film> allFilms(){
        return (List<Film>) filmHashMap.values();
    }

    @PostMapping("/addfilm")
    public Film createdFilm(@RequestBody Film film) {
        if (film.getReleaseDate() < 1985 || film.getDurations() < 0 || film.getName().isEmpty()){
            throw new RuntimeException("Ошибка");
        }
        log.debug("Добавление фильма идет");
        filmHashMap.put(film.hashCode(), film);
        return film;
    }

    @PutMapping("/updatefilm")
    public Film updateFilm(@RequestBody Film film){
        if (film.getReleaseDate() < 1985 || film.getDurations() < 0 || film.getName().isEmpty()){
            throw new RuntimeException("Ошибка");
        }
        log.debug("Обновление фильма идет");
        filmHashMap.put(film.hashCode(), film);
        return film;
    }
}
