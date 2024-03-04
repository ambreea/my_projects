package main;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private int age;
    private String address;
    private double salary;
    private Department department;


    public Employee() {
    }

    public Employee(int id, String name, int age, String address, double salary, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String name, int age, String address, double salary, Department department) {

        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.department = department;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        String s = ((id == 0) ? "" : ("id: " + id)) +
                ((name == null) ? "" : ", name: " + name) +
                ((age == 0) ? "" : ", age: " + age) +
                ((address == null) ? "" : ", address: " + address) +
                ((salary == 0) ? "" : ", salary: " + salary) + department.getName() + "\n";
        return (s.startsWith(",") ? s.substring(1, s.length()) : s);
    }
}
