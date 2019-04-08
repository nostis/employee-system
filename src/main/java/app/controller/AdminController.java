package app.controller;

import app.model.Employee;
import app.service.EmployeeService;
import app.util.Content;
import app.util.EmployeeFormValidator;
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

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeFormValidator employeeFormValidator;

    @InitBinder("add")
    protected void initPersonFormBinder(WebDataBinder binder) {
        binder.addValidators(employeeFormValidator);
    }

    @GetMapping({"/panel", "/"})
    public String showPanel(Model model){
        model.addAttribute("content", new Content());
        return "admin/panel";
    }

    @PostMapping("/search")
    public String searchEmp(@ModelAttribute Content content, Model model){
        List<Employee> employees = new ArrayList<>();
        if(NumberUtils.isParsable(content.getText())){
            for(Employee e : employeeService.getAllEmps()){
                if(e.getId() == Integer.parseInt(content.getText())){ //if user give id in form
                    employees.add(e);
                    //break;
                }
            }
        }
        else{
            for(Employee e : employeeService.getAllEmps()){ //if user give string(name) in form
                if(e.getName().equals(content.getText())){
                    employees.add(e);
                }
            }
        }

        model.addAttribute("content", content);
        model.addAttribute("employees", employees);
        return "admin/search";
    }

    @GetMapping("/all")
    public String getAllEmps(Model model){
        model.addAttribute("employees", employeeService.getAllEmps());
        model.addAttribute("empty_emp", new Employee());
        return "admin/all";
    }

    @GetMapping("/add")
    public String showAddEmpForm(@ModelAttribute Employee employee){
        return "admin/add";
    }

    @PostMapping("/add")
    public String submitFormAdd(@Valid @ModelAttribute Employee employee, BindingResult bindingResult){
        employeeFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            return "/admin/add";
        }

        employeeService.addEmp(new Employee(employee.getName(), employee.getSalary()));
        return "redirect:/admin/addsuccess";
    }

    @GetMapping("/empedit")
    public String showEditEmpForm(@ModelAttribute("empty_emp") Employee employee, Model model){
        model.addAttribute("employee", employeeService.getEmpById(employee.getId()));
        return "/admin/empedit";
    }

    @PostMapping("/empedit")
    public String submitEditEmpForm(@ModelAttribute("empty_emp") Employee employee, BindingResult bindingResult, Model model){
        employeeFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("empty_emp", employee);
            return "/admin/empedit";
        }

        employeeService.editEmp(employee.getId(), employee);
        return "redirect:/admin/editsuccess";
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
