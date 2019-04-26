package app.controller.admin.user;

import app.controller.admin.AdminController;
import app.model.CustomUserDetails;
import app.model.Employee;
import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/admin/user")
public class UserController extends AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/panel")
    public String showPanel(){
        return "/admin/user/panel";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model, Authentication authentication) {
        List<User> toModel = userService.getAllUsers();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        toModel.remove(userService.findUserByLogin(customUserDetails.getUsername()).get()); //prevent to remove user which is already logged in by removing him from list

        model.addAttribute("users", toModel);

        model.addAttribute("empty_user_del", new User());
        return "/admin/user/all";
    }

    @PostMapping("/delconfirmation")
    public String showConfirmationDelete(@ModelAttribute("empty_user_del") User user, Model model){
        model.addAttribute("user", userService.findUserById(user.getId()).get());

        return "/admin/user/delconfirmation";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute User user, @RequestParam(value="action") String action){
        if (action.equals("yes")) {
            userService.deleteById(user.getId());

            return "redirect:/admin/user/delsuccess";
        }
        return "redirect:/admin/user/panel";

    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute User user, Model model){
        model.addAttribute("allRoles", roleService.getAllRoles());
        List<Employee> empsModel = employeeService.getAllEmps();

        for(User u : userService.getAllUsers()){ //removing employees from selectable list who are already assigned to users
            if(u.getEmpId() > 0){
                Employee toRemove = empsModel
                        .stream()
                        .filter(employee -> employee.getId() == u.getEmpId())
                        .findAny()
                        .get();

                empsModel.remove(toRemove);
            }
        }

        model.addAttribute("employees", empsModel);

        return "admin/user/add";
    }

    @PostMapping("/add")
    public String submitFormAdd(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        userAddFormValidator.validate(user, bindingResult);

        List<Role> allRoles = roleService.getAllRoles();

        if(bindingResult.hasErrors()){
            model.addAttribute("allRoles", allRoles);
            model.addAttribute("employees", employeeService.getAllEmps());

            return "/admin/user/add";
        }

        Optional<Role> employeeRole = roleService.getAllRoles()
                .stream()
                .filter(role -> role.getRole().equals("EMPLOYEE"))
                .findAny();


        if(user.getEmpId() == 0){
            user.getRoles().remove(employeeRole.get()); //removing employee role if admin while creating user choose 'no employee'
        }
        else if(!user.getRoles().contains(employeeRole.get())){ //otherwise if user admin choose employee assigned to user and choose roles without employee
            Set<Role> toUser = user.getRoles();
            toUser.add(employeeRole.get());
            user.setRoles(toUser);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);

        return "redirect:/admin/user/addsuccess";
    }

    @GetMapping("/delsuccess")
    public String delSuccess(){
        return "/admin/user/delsuccess";
    }

    @GetMapping("/addsuccess")
    public String addSuccess(){
        return "/admin/user/addsuccess";
    }
}
