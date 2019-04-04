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

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeFormValidator employeeFormValidator;

    @InitBinder
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
        Employee employee = new Employee();
        if(NumberUtils.isParsable(content.getText())){
            for(Employee e : employeeService.getAllEmps()){
                if(e.getId() == Integer.parseInt(content.getText())){
                    employee = e;
                    break;
                }
            }
        }
        else{
            for(Employee e : employeeService.getAllEmps()){
                if(e.getName().equals(content.getText())){
                    employee = e;
                    break;
                }
            }
        }

        model.addAttribute("content", content);
        model.addAttribute("employee", employee);
        return "admin/search";
    }

    @PostMapping("/all")
    public String getAllEmps(Model model){
        model.addAttribute("employees", employeeService.getAllEmps());
        return "admin/all";
    }

    @GetMapping("/addform")
    public String showAddEmpForm(Employee employee){
        return "admin/addform";
    }

    @PostMapping("/addform")
    public String submitFormAdd(@Valid Employee employee, BindingResult bindingResult){
        employeeFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            return "/admin/addform";
        }

        employeeService.addEmp(new Employee(employee.getName(), employee.getSalary()));
        return "redirect:/admin/addsuccess";
    }

    @GetMapping("/addsuccess")
    public String addSuccess(){
        return "/admin/addsuccess";
    }


}
