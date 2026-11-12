package org.example.filmorate.Controllers;

import org.example.filmorate.exceptions.NotFoundException;
import org.example.filmorate.exceptions.ValidationException;
import org.example.filmorate.models.User2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserController2Test {

    private UserController2 userController;

    @BeforeEach
    void setUp() {
        userController = new UserController2();
    }

    @Test
    void shouldCreateUserSuccessfullyAndAssignId1() {
        User2 user = new User2();
        user.setEmail("test@mail.com");
        user.setLogin("validLogin");
        user.setName("Nick Name");
        user.setBirthday(LocalDate.of(1995, 5, 12));

        User2 created = userController.createdUser(user);

        assertNotNull(created);
        assertEquals(1, created.getId());

        List<User2> users = userController.allUsers();
        assertEquals(1, users.size());
        assertEquals(created, users.get(0));
    }

    @Test
    void shouldSetNameEqualToLoginWhenNameIsBlank() {
        User2 user = new User2();
        user.setEmail("test@mail.com");
        user.setLogin("myLogin");
        user.setName("   ");
        user.setBirthday(LocalDate.of(1995, 5, 12));

        User2 created = userController.createdUser(user);
        assertEquals("myLogin", created.getName());
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        User2 user = new User2();
        user.setEmail("test@mail.com");
        user.setLogin("validLogin");
        user.setBirthday(LocalDate.of(1995, 5, 12));
        User2 created = userController.createdUser(user);

        User2 updatedUser = new User2();
        updatedUser.setId(created.getId());
        updatedUser.setEmail("test@mail.com");
        updatedUser.setLogin("updatedLogin");
        updatedUser.setName("Updated Name");
        updatedUser.setBirthday(LocalDate.of(1995, 5, 12));
        userController.updateUser(updatedUser);

        User2 stored = userController.allUsers().get(0);
        assertEquals("updatedLogin", stored.getLogin());
        assertEquals("Updated Name", stored.getName());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingNonExistentUser() {
        User2 user = new User2();
        user.setId(999);
        user.setEmail("test@mail.com");
        user.setLogin("validLogin");
        user.setBirthday(LocalDate.of(1995, 5, 12));

        assertThrows(NotFoundException.class, () -> userController.updateUser(user));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        User2 user = new User2();
        user.setEmail("invalidemail.ru");
        user.setLogin("validLogin");
        user.setBirthday(LocalDate.of(1995, 5, 12));

        assertThrows(ValidationException.class, () -> userController.createdUser(user));
    }
}
