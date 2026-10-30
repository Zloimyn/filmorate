package org.example.filmorate.Controllers;

import org.example.filmorate.models.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController2 {
    private int id= 0;
    private final HashMap<Integer, User2> userHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController2.class);

    @GetMapping("/allusers")
    public List<User2> allUsers(){
        ArrayList<User2> list = new ArrayList<>(userHashMap.values());
        return list;
    }

    @PostMapping("/adduser")
    public User2 createdUser(@RequestBody User2 user2) {
        validateUser(user2);

        id++;
        user2.setId(id);
        log.debug("Добавление User: id={}, login={}", user2.getId(), user2.getLogin());
        userHashMap.put(id, user2);
        return user2;
    }

    @PutMapping("/updateuser")
    public User2 updateUser(@RequestBody User2 user2){
        if (user2 == null || user2.getId() == null || !userHashMap.containsKey(user2.getId())) {
            throw new RuntimeException("Ошибка");
        }

        validateUser(user2);

        log.debug("Обновление User: id={}, login={}", user2.getId(), user2.getLogin());
        userHashMap.put(user2.getId(), user2);
        return user2;
    }

    private void validateUser(User2 user2) {
        if (user2 == null || user2.getEmail() == null || user2.getEmail().isBlank()
            || user2.getLogin() == null || user2.getLogin().isBlank()
            || user2.getBirthday() == null || user2.getBirthday().isAfter(LocalDate.now())) {
            throw new RuntimeException("Ошибка");
        }

        if (user2.getName() == null || user2.getName().isBlank()) {
            user2.setName(user2.getLogin());
        }
    }
}
