package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component("UserDAOSimpleImpl")
public class UserDAOSimpleImpl implements UserDAO {
    private List<User> users;

    public UserDAOSimpleImpl() {
        users = new ArrayList<>();

        users.add(new User(1, "User1"));
        users.add(new User(2, "User2"));
        users.add(new User(3, "User3"));
    }

    @Override
    public User getUserById(int id) {
        if(id >= users.size() || id < 0){
            return null;
        }
        else{
            return users.get(id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
