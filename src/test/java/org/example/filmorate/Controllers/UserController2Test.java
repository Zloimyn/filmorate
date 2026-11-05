package org.example.filmorate.Controllers;

import org.example.filmorate.exceptions.ValidationException;
import org.example.filmorate.models.User2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserController2Test {

    private UserController2 userController;

    @BeforeEach
    void setUp() {
        userController = new UserController2();
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        User2 user = new User2();
        user.setEmail("invalidemail.ru");
        user.setLogin("validLogin");
        user.setBirthday(LocalDate.of(1995, 5, 12));

        assertThrows(ValidationException.class, () -> userController.createdUser(user));
    }

    @Test
    void shouldThrowExceptionWhenLoginContainsSpaces() {
        User2 user = new User2();
        user.setEmail("test@test.ru");
        user.setLogin("login with space");
        user.setBirthday(LocalDate.of(1995, 5, 12));

        assertThrows(ValidationException.class, () -> userController.createdUser(user));
    }

    @Test
    void shouldThrowExceptionWhenBirthdayIsInFuture() {
        User2 user = new User2();
        user.setEmail("test@test.ru");
        user.setLogin("validLogin");
        user.setBirthday(LocalDate.now().plusDays(1));

        assertThrows(ValidationException.class, () -> userController.createdUser(user));
    }

    @Test
    void shouldHandleNullRequest() {
        assertThrows(ValidationException.class, () -> userController.createdUser(null));
    }
}