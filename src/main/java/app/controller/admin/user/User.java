package app.controller.admin.user;

import app.controller.admin.AdminController;
import app.dao.UserDAOCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/user")
public class User extends AdminController {

    @Autowired
    UserDAOCrud userDAOCrud;

    @GetMapping("/panel")
    public String showPanel(){
        return "/admin/user/panel";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model){
        model.addAttribute("users", userDAOCrud.findAll());
        return "/admin/user/all";
    }
}
