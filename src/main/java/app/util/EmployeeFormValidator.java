package app.util;

import app.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Component
public class EmployeeFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "field.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "salary", "field.salary.empty");

        Employee e = (Employee) o;

        if(e.getName().matches(".*\\d+.*")){
            errors.rejectValue("name", "cant.contain");
        }
    }
}
