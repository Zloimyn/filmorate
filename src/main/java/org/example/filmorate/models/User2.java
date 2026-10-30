package org.example.filmorate.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class User2 {
    private int id;
    String email;
    String login;
    String name;
    LocalDate birthday;
}
