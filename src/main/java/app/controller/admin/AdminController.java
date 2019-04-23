package app.controller.admin;

import app.service.EmployeeService;
import app.util.Content;
import app.util.EmployeeAddFormValidator;
import app.util.EmployeeEditFormValidator;
import app.util.UserAddFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    protected EmployeeService employeeService;
    @Autowired
    protected EmployeeAddFormValidator employeeAddFormValidator;
    @Autowired
    protected EmployeeEditFormValidator employeeEditFormValidator;
    @Autowired
    protected UserAddFormValidator userAddFormValidator;

    @InitBinder("add")
    protected void initPersonAddFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeAddFormValidator);
    }

    @InitBinder("edit")
    protected void initPersonEditFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeEditFormValidator);
    }

    @GetMapping("")
    public String showPanel(Model model){
        model.addAttribute("content", new Content());
        return "/admin/panel";
    }

}
