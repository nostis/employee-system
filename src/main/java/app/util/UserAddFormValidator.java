package app.util;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserAddFormValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "field.login.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "field.password.empty");

        User u = (User) o;

        for(User user : userService.getAllUsers()){
            if(user.getLogin().equals(u.getLogin())){
                errors.rejectValue("login", "already.exist");
            }
        }

        if(u.getRoles().size() == 0){
            errors.rejectValue("roles", "field.role.empty");
        }

    }
}
