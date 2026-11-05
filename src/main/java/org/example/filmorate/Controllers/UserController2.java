package org.example.filmorate.Controllers;

import org.example.filmorate.exceptions.NotFoundException;
import org.example.filmorate.exceptions.ValidationException;
import org.example.filmorate.models.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController2 {
    private int id = 0;
    private final HashMap<Integer, User2> userHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController2.class);

    @GetMapping
    public List<User2> allUsers() {
        return new ArrayList<>(userHashMap.values());
    }

    @PostMapping
    public User2 createdUser(@RequestBody(required = false) User2 user2) {
        if (user2 == null) {
            log.error("Ошибка: передан пустой запрос на создание пользователя");
            throw new ValidationException("Тело запроса не может быть пустым");
        }
        validateUser(user2);

        id++;
        user2.setId(id);
        log.info("Добавление User: id={}, login={}", user2.getId(), user2.getLogin());
        userHashMap.put(id, user2);
        return user2;
    }

    @PutMapping
    public User2 updateUser(@RequestBody(required = false) User2 user2) {
        if (user2 == null) {
            log.error("Ошибка при обновлении пользователя: пустой запрос");
            throw new ValidationException("Запрос не может быть пустым");
        }
        if (user2.getId() == null || !userHashMap.containsKey(user2.getId())) {
            log.error("Ошибка при обновлении пользователя: пользователь с id={} не найден", user2.getId());
            throw new NotFoundException("Пользователь с таким id не найден");
        }

        validateUser(user2);

        log.info("Обновление User: id={}, login={}", user2.getId(), user2.getLogin());
        userHashMap.put(user2.getId(), user2);
        return user2;
    }

    private void validateUser(User2 user2) {
        if (user2.getEmail() == null || user2.getEmail().isBlank() || !user2.getEmail().contains("@")) {
            log.error("Ошибка email: {}", user2.getEmail());
            throw new ValidationException("Почта не может быть пустой и должна содержать символ '@'");
        }
        if (user2.getLogin() == null || user2.getLogin().isBlank() || user2.getLogin().contains(" ")) {
            log.error("Ошибка login: {}", user2.getLogin());
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user2.getBirthday() == null || user2.getBirthday().isAfter(LocalDate.now())) {
            log.error("Ошибка birthday: {}", user2.getBirthday());
            throw new ValidationException("Дата рождения не может быть в будущем");
        }

        if (user2.getName() == null || user2.getName().isBlank()) {
            user2.setName(user2.getLogin());
        }
    }
}