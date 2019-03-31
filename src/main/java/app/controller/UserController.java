package app.controller;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import app.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        String toReturn = "Id: " + user.getId() + " Name: " + user.getName();

        return toReturn; //to do; must return Optional<User>
    }
}
