package app.dao;

import app.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOSimpleImpl implements EmployeeDAO {
    private List<Employee> employees;

    public EmployeeDAOSimpleImpl() {
        employees = new ArrayList<>();

        employees.add(new Employee("Emp1", 1000));
        employees.add(new Employee("Emp2", 1000));
        employees.add(new Employee("Emp3", 2000));
    }

    @Override
    public Employee getEmpById(int id) {
        for(Employee e : employees){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    @Override
    public Employee getEmpByName(String name) {
        for(Employee e : employees){
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getEmpsBySalary(int salary) {
        List<Employee> list = new ArrayList<>();

        for(Employee e : employees){
            if(e.getSalary() == salary || e.getSalary() == salary - 500 || e.getSalary() == salary + 500){
                list.add(e);
            }
        }

        return list;
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
        for(Employee e : employees){
            if(e.getId() == id){
                employees.remove(e);
                break;
            }
        }

        employees.add(employee);
    }

    @Override
    public void deleteEmp(int id) {
        for(Employee e : employees){
            if(e.getId() == id){
                employees.remove(e);
                break;
            }
        }
    }
}
