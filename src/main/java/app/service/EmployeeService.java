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

    public Employee getEmpById(int id){
        return employeeDAO.getEmpById(id);
    }

    public Employee getEmpByName(String name){
        return employeeDAO.getEmpByName(name);
    }

    public List<Employee> getEmpsBySalary(int salary){
        return employeeDAO.getEmpsBySalary(salary);
    }

    public List<Employee> getAllEmps(){
        return employeeDAO.getAllEmps();
    }

    public void addEmp(Employee employee){
        employeeDAO.addEmp(employee);
    }

    public void editEmp(int id, Employee employee){
        employeeDAO.editEmp(id, employee);
    }

    public void deleteEmp(int id){
        employeeDAO.deleteEmp(id);
    }
}
