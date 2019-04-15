package app.dao;

import app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAOCrud extends CrudRepository<User, Integer> {
}
