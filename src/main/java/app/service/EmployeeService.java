package app.service;

import app.dao.EmployeeDAOCrud;
import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAOCrud employeeDAOCrud;

    public Optional<Employee> findEmpById(int id){
        return employeeDAOCrud.findById(id);
    }

    public List<Employee> getEmpsBySalary(int salary){
        return employeeDAOCrud.findBySalary(salary);
    }

    public List<Employee> getAllEmps(){
        List<Employee> list = new ArrayList<>();
        employeeDAOCrud.findAll().forEach(list::add);
        return list;
    }

    public List<Employee> getEmpsByName(String name){
        return employeeDAOCrud.findByName(name);
    }

    public void saveEmp(Employee employee){
        employeeDAOCrud.save(employee);
    }

    public void editEmp(Employee employee){
        employeeDAOCrud.save(employee);
    }

    public void deleteById(int id){
        employeeDAOCrud.deleteById(id);
    }
}
