package app.service;

import app.dao.UserDAOCrud;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAOCrud userDAOCrud;

    public List<User> getAllUsers() {
        return userDAOCrud.findAll();
    }

    public Optional<User> findUserById(int id){
        return userDAOCrud.findById(id);
    }

    public void deleteById(int id){
        userDAOCrud.deleteById(id);
    }

    public Optional<User> findUserByLogin(String login){
        return userDAOCrud.findByLogin(login);
    }
}
