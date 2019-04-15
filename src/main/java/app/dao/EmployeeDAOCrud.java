package app.dao;

import app.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAOCrud extends CrudRepository<Employee, Integer>, EmployeeDAOCustom<Employee, Integer> {
    @Query("select e from Employee e where e.salary = :salary or (e.salary >= (:salary - 500) and e.salary <= (:salary + 500))")
    @Override
    List<Employee> findBySalary(@Param("salary")int salary);
}
