package app.dao;

import app.model.Employee;

import java.util.List;

public interface UserDAO {
    Employee getUserById(int id);
    List<Employee> getAllUsers();
    void addUser(Employee employee);
}
