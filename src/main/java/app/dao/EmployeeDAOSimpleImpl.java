package app.dao;

import app.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeDAOSimpleImpl implements EmployeeDAO {
    private List<Employee> employees;

    public EmployeeDAOSimpleImpl() {
        employees = new ArrayList<>();

        employees.add(new Employee("Jack", 1000));
        employees.add(new Employee("Hanna", 1000));
        employees.add(new Employee("John", 2000));
    }

    @Override
    public Optional<Employee> getEmpById(int id) {
        return employees
                .stream()
                .filter(employee -> id == employee.getId())
                .findAny();
    }

    @Override
    public List<Employee> getEmpsBySalary(int salary) {
        return employees
                .stream()
                .filter(employee -> salary == employee.getSalary() || (salary >= employee.getSalary() - 500 && salary <= employee.getSalary() + 500))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmpsByName(String name) {
        return employees
                .stream()
                .filter(employee -> name.equals(employee.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmps() {
        return employees;
    }

    @Override
    public void addEmp(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void editEmp(int id, Employee employee) {
        employees.set(employees.indexOf(this.getEmpById(id).get()), employee);
    }

    @Override
    public void deleteEmp(int id) {
        employees.remove(this.getEmpById(id).get());
    }
}
