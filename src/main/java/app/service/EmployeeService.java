package app.service;

import app.dao.EmployeeDAOCrud;
import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAOCrud employeeDAOCrud;

    public Optional<Employee> findById(int id){
        return employeeDAOCrud.findById(id);
    }

    public List<Employee> getEmpsBySalary(int salary){
        return employeeDAOCrud.findBySalary(salary);
    }

    public List<Employee> findAll(){
        List<Employee> list = new ArrayList<>();
        employeeDAOCrud.findAll().forEach(list::add);
        return list;
    }

    public List<Employee> getEmpsByName(String name){
        return employeeDAOCrud.findByName(name);
    }

    public void save(Employee employee){
        employeeDAOCrud.save(employee);
    }

    public void edit(Employee employee){
        employeeDAOCrud.save(employee);
    }

    public void deleteById(int id){
        employeeDAOCrud.deleteById(id);
    }
}
