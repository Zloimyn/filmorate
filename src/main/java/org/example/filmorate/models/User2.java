package org.example.filmorate.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class User2 {
    private Integer id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
}