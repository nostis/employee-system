package app.dao;

import app.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAOCustom extends EmployeeDAOCrud {
    List<Employee> getBySalary(int salary);
    List<Employee> getByName(String name);
}
