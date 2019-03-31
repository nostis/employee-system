package app.service;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    @Qualifier("UserDAOSimpleImpl")
    UserDAO userDAO;

    public User getUserById(int id){
        return userDAO.getUserById(id);
    }
}
