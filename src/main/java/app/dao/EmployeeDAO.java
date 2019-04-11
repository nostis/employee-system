package app.dao;

import app.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    Optional<Employee> getEmpById(int id);
    List<Employee> getEmpsBySalary(int salary);
    List<Employee> getEmpsByName(String name);
    List<Employee> getAllEmps();
    void addEmp(Employee employee);
    void editEmp(int id, Employee employee);
    void deleteEmp(int id);
}
