package org.example.filmorate.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class User2 {
    private int id;
    String email;
    String login;
    String name;
    int birthday;
}
