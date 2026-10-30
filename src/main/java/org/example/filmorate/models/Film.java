package org.example.filmorate.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Film {
    private int id;
    String name;
    String descriptions;
    int releaseDate;
    int durations;
}
