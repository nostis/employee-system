package app.dao;

import app.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAOCrud extends CrudRepository<Employee, Integer> {
}
