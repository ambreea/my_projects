package main;

import java.io.Serializable;
import java.util.Set;

public class Department implements Serializable {
    private int departmentId;
    private String name;
    private Set<Employee> employeeSet;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(int departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public String toString() {
        return "id:" + departmentId +
                ", name:" + name + "\n";
    }
}




