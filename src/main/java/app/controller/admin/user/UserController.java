package app.controller.admin.user;

import app.controller.admin.AdminController;
import app.dao.UserDAOCrud;
import app.model.CustomUserDetails;
import app.model.Employee;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController extends AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/panel")
    public String showPanel(){
        return "/admin/user/panel";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model, Authentication authentication) {
        List<User> toModel = userService.getAllUsers();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        toModel.remove(userService.findUserByLogin(customUserDetails.getUsername()).get()); //prevent to remove user which is already logged in by removing him from list

        model.addAttribute("users", toModel);

        model.addAttribute("empty_user_del", new User());
        return "/admin/user/all";
    }

    @PostMapping("/delconfirmation")
    public String showConfirmationDelete(@ModelAttribute("empty_user_del") User user, Model model){
        model.addAttribute("user", userService.findUserById(user.getId()).get());

        return "/admin/user/delconfirmation";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute User user, @RequestParam(value="action") String action){
        if (action.equals("yes")) {
            userService.deleteById(user.getId());

            return "redirect:/admin/user/delsuccess";
        }
        return "redirect:/admin/user/panel";

    }

    @GetMapping("/delsuccess")
    public String delSuccess(){
        return "/admin/user/delsuccess";
    }
}
