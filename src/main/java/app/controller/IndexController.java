package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/", "/index"})
    public String showIndex(){
        return "index";
    }

    @GetMapping("/admin")
    public String showAdminPanel(){
        return "admin/panel";
    }

    @GetMapping("/employee")
    public String showEmployeePanel(){
        return "employee/panel";
    }

    @GetMapping("/accountant")
    public String showAccountantPanel(){
        return "accountant/panel";
    }
}
