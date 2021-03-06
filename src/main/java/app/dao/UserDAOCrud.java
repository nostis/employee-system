package app.dao;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAOCrud extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String s);
}
