package app.service;

import app.dao.EmployeeDAO;
import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAOSimpleImpl;

    public Employee getEmpById(int id){
        return employeeDAOSimpleImpl.getEmpById(id);
    }

    public Employee getEmpByName(String name){
        return employeeDAOSimpleImpl.getEmpByName(name);
    }

    public List<Employee> getEmpsBySalary(int salary){
        return employeeDAOSimpleImpl.getEmpsBySalary(salary);
    }

    public List<Employee> getAllEmps(){
        return employeeDAOSimpleImpl.getAllEmps();
    }

    public void addEmp(Employee employee){
        employeeDAOSimpleImpl.addEmp(employee);
    }

    public void editEmp(int id, Employee employee){
        employeeDAOSimpleImpl.editEmp(id, employee);
    }

    public void deleteEmp(int id){
        employeeDAOSimpleImpl.deleteEmp(id);
    }
}
