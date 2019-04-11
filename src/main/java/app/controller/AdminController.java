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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeAddFormValidator employeeAddFormValidator;
    @Autowired
    private EmployeeEditFormValidator employeeEditFormValidator;

    @InitBinder("add")
    protected void initPersonAddFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeAddFormValidator);
    }

    @InitBinder("edit")
    protected void initPersonEditFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeEditFormValidator);
    }

    @GetMapping({"/panel", "/"})
    public String showPanel(Model model){
        model.addAttribute("content", new Content());
        return "admin/panel";
    }

    @PostMapping("/search")
    public String searchEmp(@ModelAttribute Content content, Model model){
        model.addAttribute("empty_emp_edit", new Employee());
        model.addAttribute("empty_emp_del", new Employee());

        List<Employee> employees = new ArrayList<>();

        if(NumberUtils.isParsable(content.getText())){ //if user typed number in form
            employees.addAll(employeeService.getEmpsBySalary(Integer.parseInt(content.getText())));

            Optional<Employee> optionalEmployee = employeeService.getEmpById(Integer.parseInt(content.getText()));
            optionalEmployee.ifPresent(employees::add);
        }
        else{
            employees.addAll(employeeService.getEmpsByName(content.getText()));
        }

        model.addAttribute("content", content);
        model.addAttribute("employees", employees);

        return "admin/search";
    }

    @GetMapping("/all")
    public String getAllEmps(Model model){
        model.addAttribute("employees", employeeService.getAllEmps());
        model.addAttribute("empty_emp_edit", new Employee());
        model.addAttribute("empty_emp_del", new Employee());

        return "admin/all";
    }

    @GetMapping("/add")
    public String showAddEmpForm(@ModelAttribute Employee employee){
        return "admin/add";
    }

    @PostMapping("/add")
    public String submitFormAdd(@Valid @ModelAttribute Employee employee, BindingResult bindingResult){
        employeeAddFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            return "/admin/add";
        }

        employeeService.addEmp(new Employee(employee.getName(), employee.getSalary()));

        return "redirect:/admin/addsuccess";
    }

    @GetMapping("/edit")
    public String showEditEmpForm(@ModelAttribute("empty_emp_edit") Employee employee, Model model){
        model.addAttribute("employee", employeeService.getEmpById(employee.getId()).get());

        return "/admin/edit";
    }

    @PostMapping("/edit")
    public String submitEditEmpForm(@ModelAttribute("empty_emp_edit") Employee employee, BindingResult bindingResult, Model model){
        employeeEditFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("empty_emp_edit", employee);

            return "/admin/edit";
        }

        employeeService.editEmp(employee.getId(), employee);

        return "redirect:/admin/editsuccess";
    }

    @PostMapping("/delconfirmation")
    public String showConfirmationDelete(@ModelAttribute("empty_emp_del") Employee employee, Model model){
        model.addAttribute("employee", employeeService.getEmpById(employee.getId()).get());

        return "/admin/delconfirmation";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@ModelAttribute Employee employee, @RequestParam(value="action") String action){
        switch(action){
            case "yes":{
                employeeService.deleteEmp(employee.getId());

                return "redirect:/admin/delsuccess";
            }
            case "no":{
                return "redirect:/admin/panel";
            }
            default:{
                return "redirect:/admin/panel";
            }
        }

    }

    @GetMapping("/delsuccess")
    public String delSuccess(){
        return "/admin/delsuccess";
    }

    @GetMapping("/editsuccess")
    public String editSuccess(){
        return "/admin/editsuccess";
    }

    @GetMapping("/addsuccess")
    public String addSuccess(){
        return "/admin/addsuccess";
    }
}
