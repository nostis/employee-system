package app.controller.admin;

import app.model.Employee;
import app.service.EmployeeService;
import app.util.Content;
import app.util.EmployeeAddFormValidator;
import app.util.EmployeeEditFormValidator;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    protected EmployeeService employeeService;
    @Autowired
    protected EmployeeAddFormValidator employeeAddFormValidator;
    @Autowired
    protected EmployeeEditFormValidator employeeEditFormValidator;

    @InitBinder("add")
    protected void initPersonAddFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeAddFormValidator);
    }

    @InitBinder("edit")
    protected void initPersonEditFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeEditFormValidator);
    }

    @GetMapping({"/panel", ""})
    public String showPanel(Model model){
        model.addAttribute("content", new Content());
        return "/admin/panel";
    }

}
