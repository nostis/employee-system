package app.service;

import app.dao.UserDAO;
import app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    @Qualifier("UserDAOSimpleImpl")
    UserDAO userDAO;

    public Employee getUserById(int id){
        return userDAO.getUserById(id);
    }

    public List<Employee> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void addUser(Employee employee){
        userDAO.addUser(employee);
    }
}
