package app.service;

import app.dao.EmployeeDAO;
import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAOSimpleImpl;

    public Optional<Employee> getEmpById(int id){
        return employeeDAOSimpleImpl.getEmpById(id);
    }

    public List<Employee> getEmpsBySalary(int salary){
        return employeeDAOSimpleImpl.getEmpsBySalary(salary);
    }

    public List<Employee> getAllEmps(){
        return employeeDAOSimpleImpl.getAllEmps();
    }

    public List<Employee> getEmpsByName(String name){
        return employeeDAOSimpleImpl.getEmpsByName(name);
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
