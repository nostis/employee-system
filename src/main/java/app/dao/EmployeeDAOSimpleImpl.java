package app.dao;

import app.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component("EmployeeDAOSimpleImpl")
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
        //return employees.get(id);
        Employee employee = new Employee();
        for(Employee e : employees){
            if(e.getId() == id){
                employee = e;
                break;
            }
        }

        return employee;
    }

    @Override
    public Employee getEmpByName(String name) {
        Employee e = new Employee();

        for(Employee em : employees){
            if(em.getName().equals(name)){
                e = em;
                break;
            }
        }
        return e;
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
