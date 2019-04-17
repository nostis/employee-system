package app.controller;

import app.model.CustomUserDetails;
import app.model.Employee;
import app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String getEmpByLoggedUser(Authentication authentication, Model model){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Employee> optEmployee = employeeService.findEmpById(userDetails.getEmpId());

        if(optEmployee.isPresent()){
            model.addAttribute("employee", optEmployee.get());
        }
        else{
            model.addAttribute("employee", null);
        }

        return "employee/employee";
    }
}
