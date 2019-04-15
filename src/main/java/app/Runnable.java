package app;

import app.dao.EmployeeDAOCrud;
import app.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Runnable {
    public static void main(String[] args) {
        SpringApplication.run(Runnable.class, args);
    }

    @Bean
    public CommandLineRunner demoData(EmployeeDAOCrud repo) {
        return args -> {
            repo.save(new Employee("Jack", 1000));
            repo.save(new Employee("Hanna", 2000));
            repo.save(new Employee("John", 1000));
        };
    }
}
