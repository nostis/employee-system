package app.dao;

import app.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDAOCrud extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
