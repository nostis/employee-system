package app.dao;

import app.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAOCustom<T, S> {
    List<Employee> findBySalary(int salary);
    List<Employee> findByName(String name);
}
