package app.dao;

import app.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component("UserDAOSimpleImpl")
public class UserDAOSimpleImpl implements UserDAO {
    private List<Employee> employees;

    public UserDAOSimpleImpl() {
        employees = new ArrayList<>();

        employees.add(new Employee(1, "User1"));
        employees.add(new Employee(2, "User2"));
        employees.add(new Employee(3, "User3"));
    }

    @Override
    public Employee getUserById(int id) {
        if(id >= employees.size() || id < 0){
            return null;
        }
        else{
            return employees.get(id);
        }
    }

    @Override
    public List<Employee> getAllUsers() {
        return employees;
    }

    @Override
    public void addUser(Employee employee) {
        employees.add(employee);
    }
}
