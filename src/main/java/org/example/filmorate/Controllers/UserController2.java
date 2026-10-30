package org.example.filmorate.Controllers;

import org.example.filmorate.models.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController2 {
    private final HashMap<Integer, User2> userHashMap = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController2.class);

    @GetMapping("/allusers")
    public List<User2> allUsers(){
        return (List<User2>) userHashMap.values();
    }

    @PostMapping("/adduser")
    public User2 createdUser(@RequestBody User2 user2) {
        if (user2.getEmail().isEmpty() || user2.getLogin().isEmpty() || user2.getLogin().isBlank() || user2.getBirthday() > 2026){
            throw new RuntimeException("Ошибка");
        } else if (user2.getName().isEmpty() || user2.getName().isBlank()) {
            user2.setName(user2.getLogin());
        }
        log.debug("Добавление User идет");
        userHashMap.put(user2.hashCode(), user2);
        return user2;
    }

    @PutMapping("/updateuser")
    public User2 updateUser(@RequestBody User2 user2){
        if (user2.getEmail().isEmpty() || user2.getLogin().isEmpty() || user2.getLogin().isBlank() || user2.getBirthday() > 2026){
            throw new RuntimeException("Ошибка");
        } else if (user2.getName().isEmpty() || user2.getName().isBlank()) {
            user2.setName(user2.getLogin());
        }
        log.debug("Обновление User идет");
        userHashMap.put(user2.hashCode(), user2);
        return user2;
    }
}
