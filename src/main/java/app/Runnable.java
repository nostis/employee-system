package app;

import app.dao.EmployeeDAOCrud;
import app.dao.RoleDAOCrud;
import app.dao.UserDAOCrud;
import app.model.Employee;
import app.model.Role;
import app.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.expression.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Runnable {
    public static void main(String[] args) {
        SpringApplication.run(Runnable.class, args);
    }

    @Bean
    public CommandLineRunner demoData(EmployeeDAOCrud emps, UserDAOCrud users, RoleDAOCrud roles) {
        return args -> {
            emps.save(new Employee("Jack", 1000));
            emps.save(new Employee("Hanna", 2000));
            emps.save(new Employee("John", 1000));

            //users.save(new User())
            roles.save(new Role("ADMIN"));
            roles.save(new Role("ACCOUNTANT"));
            roles.save(new Role("EMPLOYEE"));

            Set<Role> admin = new HashSet<Role>() {{
                add(roles.findByRole("ADMIN"));
            }};

            Set<Role> accountant = new HashSet<Role>() {{
                add(roles.findByRole("ACCOUNTANT"));
            }};

            Set<Role> employee = new HashSet<Role>() {{
                add(roles.findByRole("EMPLOYEE"));
            }};

            users.save(new User("admin", "$2a$11$cmPaJm9UbNaxGdCddO8qQOrqexkZ5F5QSRLPLY1gkS1l2wzTR2656", 0, admin)); //hashed 'password'
            users.save(new User("acc", "$2a$11$cmPaJm9UbNaxGdCddO8qQOrqexkZ5F5QSRLPLY1gkS1l2wzTR2656", 0, accountant));
            users.save(new User("emp", "$2a$11$cmPaJm9UbNaxGdCddO8qQOrqexkZ5F5QSRLPLY1gkS1l2wzTR2656", 1, employee));
            users.save(new User("emp2", "$2a$11$cmPaJm9UbNaxGdCddO8qQOrqexkZ5F5QSRLPLY1gkS1l2wzTR2656", 2, employee));
            users.save(new User("admin2", "$2a$11$cmPaJm9UbNaxGdCddO8qQOrqexkZ5F5QSRLPLY1gkS1l2wzTR2656", 0, admin));
        };
    }
}
