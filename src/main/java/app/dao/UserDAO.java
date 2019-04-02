package app.dao;

import app.model.User;

import java.util.List;

public interface UserDAO {
    User getUserById(int id);
    List<User> getAllUsers();
    void addUser(User user);
}
