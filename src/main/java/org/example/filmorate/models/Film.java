package org.example.filmorate.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Film {
    private int id;
    String name;
    String descriptions;
    LocalDate releaseDate;
    int durations;
}
