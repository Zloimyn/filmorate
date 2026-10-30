package org.example.filmorate.Controllers;

import org.example.filmorate.models.Film;
import org.example.filmorate.models.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class FilmController {
    private int id= 0;
    private final HashMap<Integer, Film> filmHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @GetMapping("/allfilms")
    public List<Film> allFilms(){
        ArrayList<Film> list = new ArrayList<>(filmHashMap.values());
        return list;
    }

    @PostMapping("/addfilm")
    public Film createdFilm(@RequestBody Film film) {
        if (film.getReleaseDate() < 1985 || film.getDurations() < 0 || film.getName().isEmpty()){
            throw new RuntimeException("Ошибка");
        }
        id++;
        film.setId(id);
        log.debug("Добавление фильма идет");
        filmHashMap.put(id, film);
        return film;
    }

    @PutMapping("/updatefilm")
    public Film updateFilm(@RequestBody Film film){
        if (film.getId() <= 0 || !filmHashMap.containsKey(film.getId())) {
            throw new RuntimeException("Фильм с таким id не найден");
        }
        if (film.getReleaseDate() < 1985 || film.getDurations() < 0 || film.getName().isEmpty()){
            throw new RuntimeException("Ошибка");
        }
        log.debug("Обновление фильма идет");
        filmHashMap.put(film.getId(), film);
        return film;
    }
}
