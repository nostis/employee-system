package app.controller;

import app.model.Employee;
import app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(name="id") int id, Model model){
        Optional<Employee> optEmployee = Optional.ofNullable(employeeService.getEmpById(id));

        if(optEmployee.isPresent()){
            model.addAttribute("employee", optEmployee.get());
        }
        else{
            model.addAttribute("employee", null);
        }
        
        return "employee/employee";
    }
}
