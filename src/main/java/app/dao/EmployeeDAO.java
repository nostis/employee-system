package app.dao;

import app.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmpById(int id);
    Employee getEmpByName(String name);
    List<Employee> getEmpsBySalary(int salary);
    List<Employee> getAllEmps();
    void addEmp(Employee employee);
    void editEmp(int id, Employee employee);
    void deleteEmp(int id);
}
