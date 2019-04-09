package app.util;

import app.model.Employee;
import app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeAddFormValidator implements Validator {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        boolean exist = false;

        ValidationUtils.rejectIfEmpty(errors, "name", "field.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "salary", "field.salary.empty");

        Employee e = (Employee) o;

        if(e.getName().matches(".*\\d+.*")){
            errors.rejectValue("name", "cant.contain");
        }
    }
}
