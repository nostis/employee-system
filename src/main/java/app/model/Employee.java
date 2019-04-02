package app.model;

public class Employee {
    private static int currentId = 1;
    private int id;
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.id = currentId++;
        this.name = name;
        this.salary = salary;
    }

    public Employee(){}

    public int getId() {
        return id;
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
