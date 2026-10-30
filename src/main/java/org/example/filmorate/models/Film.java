package org.example.filmorate.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Film {
    private Integer id;
    private String name;
    private String descriptions;
    private LocalDate releaseDate;
    private int durations;
}