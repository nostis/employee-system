package app.service;

import app.dao.EmployeeDAO;
import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    @Qualifier("EmployeeDAOSimpleImpl")
    EmployeeDAO employeeDAO;

    public Employee getUserById(int id){
        return employeeDAO.getUserById(id);
    }

    public List<Employee> getAllUsers(){
        return employeeDAO.getAllUsers();
    }

    public void addUser(Employee employee){
        employeeDAO.addUser(employee);
    }
}
