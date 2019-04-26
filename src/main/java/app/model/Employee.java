package app.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(){}

    @Override
    public String toString(){
        return "Id: " + id + " Name: " + name + " Salary: " + salary;
    }
}
