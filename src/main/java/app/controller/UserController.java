package app.controller;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import app.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id, Model model){
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));

        if(user.isPresent()){
            model.addAttribute("id", user.get().getId());
            model.addAttribute("name", user.get().getName());
        }
        else{
            model.addAttribute("id", "");
            model.addAttribute("name", "");
        }

        return "user";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/saveform")
    public String showForm(User user){
        return "save";
    }

    @PostMapping("/save")
    public void addUser(User user, Model model){
        userService.addUser(user);
    }
}
