package app.controller;

import app.model.CustomUserDetails;
import app.model.Employee;
import app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class SingleEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String showEmpByLoggedUser(Authentication authentication, Model model){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Optional<Employee> optEmployee = employeeService.findEmpById(userDetails.getEmpId());

        model.addAttribute("employee", optEmployee.get());

        return "single-employee/employee";
    }
}
