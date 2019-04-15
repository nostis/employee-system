package app.service;

import app.dao.EmployeeDAOCrud;
import app.dao.EmployeeDAOCustom;
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
    private EmployeeDAOCustom employeeDAOCustom;

    public Optional<Employee> findById(int id){
        return employeeDAOCustom.findById(id);
    }

    public List<Employee> getEmpsBySalary(int salary){
        return employeeDAOCustom.getBySalary(salary);
    }

    public List<Employee> findAll(){
        List<Employee> list = new ArrayList<>();
        employeeDAOCustom.findAll().forEach(list::add);
        return list;
    }

    public List<Employee> getEmpsByName(String name){
        return employeeDAOCustom.getByName(name);
    }

    public void save(Employee employee){
        employeeDAOCustom.save(employee);
    }

    public void edit(Employee employee){
        employeeDAOCustom.save(employee);
    }

    public void deleteById(int id){
        employeeDAOCustom.deleteById(id);
    }
}
