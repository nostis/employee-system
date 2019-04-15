package app.controller;

import app.model.Employee;
import app.service.EmployeeService;
import app.util.Content;
import app.util.EmployeeAddFormValidator;
import app.util.EmployeeEditFormValidator;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/accountant")
public class AccountantController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeEditFormValidator employeeEditFormValidator;


    @InitBinder("edit")
    protected void initPersonEditFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeEditFormValidator);
    }

    @GetMapping({"/panel", "/"})
    public String showPanel(Model model){
        model.addAttribute("content", new Content());
        return "/accountant/panel";
    }

    @PostMapping("/search")
    public String searchEmp(@ModelAttribute Content content, Model model){
        model.addAttribute("empty_emp_edit", new Employee());

        List<Employee> employees = new ArrayList<>();

        if(NumberUtils.isParsable(content.getText())){ //if user typed number in form
            employees.addAll(employeeService.getEmpsBySalary(Integer.parseInt(content.getText())));

            Optional<Employee> optionalEmployee = employeeService.findEmpById(Integer.parseInt(content.getText()));
            optionalEmployee.ifPresent(employees::add);
        }
        else{
            employees.addAll(employeeService.getEmpsByName(content.getText()));
        }

        model.addAttribute("content", content);
        model.addAttribute("employees", employees);

        return "/accountant/search";
    }

    @GetMapping("/all")
    public String getAllEmps(Model model){
        model.addAttribute("employees", employeeService.getAllEmps());
        model.addAttribute("empty_emp_edit", new Employee());

        return "/accountant/all";
    }

    @GetMapping("/edit")
    public String showEditEmpForm(@ModelAttribute("empty_emp_edit") Employee employee, Model model){
        model.addAttribute("employee", employeeService.findEmpById(employee.getId()).get());

        return "/accountant/edit";
    }

    @PostMapping("/edit")
    public String submitEditEmpForm(@ModelAttribute("empty_emp_edit") Employee employee, BindingResult bindingResult, Model model){
        employeeEditFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("empty_emp_edit", employee);

            return "/accountant/edit";
        }

        employeeService.editEmp(employee);

        return "redirect:/accountant/editsuccess";
    }

    @GetMapping("/editsuccess")
    public String editSuccess(){
        return "/accountant/editsuccess";
    }

    @GetMapping("/addsuccess")
    public String addSuccess(){
        return "/accountant/addsuccess";
    }
}

