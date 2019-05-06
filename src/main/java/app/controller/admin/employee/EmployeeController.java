package app.controller.admin.employee;

import app.controller.admin.AdminController;
import app.model.Employee;
import app.util.Content;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController extends AdminController {

    @GetMapping("/panel")
    public String showEmpPanel(Model model){
        model.addAttribute("content", new Content());
        return "admin/employee/panel";
    }

    @PostMapping("/search")
    public String searchEmp(@ModelAttribute Content content, Model model){
        model.addAttribute("empty_emp_edit", new Employee());
        model.addAttribute("empty_emp_del", new Employee());

        List<Employee> employees = new ArrayList<>();

        if(NumberUtils.isParsable(content.getText())){ //if user typed number in form
            employees.addAll(employeeService.getEmpsBySalary(Integer.parseInt(content.getText())));

            Optional<Employee> optionalEmployee = employeeService.findEmpById(Integer.parseInt(content.getText()));
            if(optionalEmployee.isPresent()){
                if(!employees.contains(optionalEmployee.get())){
                    employees.add(optionalEmployee.get());
                }
            }
        }
        else{
            employees.addAll(employeeService.getEmpsByName(content.getText()));
        }

        model.addAttribute("content", content);
        model.addAttribute("employees", employees);

        return "/admin/employee/search";
    }

    @GetMapping("/all")
    public String showAllEmps(Model model){
        model.addAttribute("employees", employeeService.getAllEmps());
        model.addAttribute("empty_emp_edit", new Employee());
        model.addAttribute("empty_emp_del", new Employee());

        return "/admin/employee/all";
    }

    @GetMapping("/add")
    public String showAddEmpForm(@ModelAttribute Employee employee){
        return "admin/employee/add";
    }

    @PostMapping("/add")
    public String submitFormAdd(@Valid @ModelAttribute Employee employee, BindingResult bindingResult){
        employeeAddFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            return "/admin/employee/add";
        }

        employeeService.saveEmp(new Employee(employee.getName(), employee.getSalary()));

        return "redirect:/admin/employee/addsuccess";
    }

    @GetMapping("/edit")
    public String showEditEmpForm(@ModelAttribute("empty_emp_edit") Employee employee, Model model){
        model.addAttribute("employee", employeeService.findEmpById(employee.getId()).get());

        return "/admin/employee/edit";
    }

    @PostMapping("/edit")
    public String submitEditEmpForm(@ModelAttribute("empty_emp_edit") Employee employee, BindingResult bindingResult, Model model){
        employeeEditFormValidator.validate(employee, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("empty_emp_edit", employee);

            return "/admin/employee/edit";
        }

        employeeService.editEmp(employee);

        return "redirect:/admin/employee/editsuccess";
    }

    @PostMapping("/delconfirmation")
    public String showConfirmationDelete(@ModelAttribute("empty_emp_del") Employee employee, Model model){
        model.addAttribute("employee", employeeService.findEmpById(employee.getId()).get());

        return "/admin/employee/delconfirmation";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@ModelAttribute Employee employee, @RequestParam(value="action") String action) {
        if (action.equals("yes")) {
            employeeService.deleteById(employee.getId());

            return "redirect:/admin/employee/delsuccess";
        }
        return "redirect:/admin/employee/panel";
    }

    @GetMapping("/delsuccess")
    public String delSuccess(){
        return "/admin/employee/delsuccess";
    }

    @GetMapping("/editsuccess")
    public String editSuccess(){
        return "/admin/employee/editsuccess";
    }

    @GetMapping("/addsuccess")
    public String addSuccess(){
        return "/admin/employee/addsuccess";
    }
}
