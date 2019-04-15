package app.model;


import javax.persistence.*;

@Entity
public class Employee {
    //private static int currentId = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        //this.id = currentId++;
        this.name = name;
        this.salary = salary;
    }

    public Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
