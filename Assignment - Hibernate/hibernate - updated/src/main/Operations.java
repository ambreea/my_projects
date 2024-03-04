package main;

import java.util.List;

public interface Operations {
    public void connectDb();
    public int insertEmployee(Employee employee);
    public int deleteEmployee(int id);
    public boolean modifyEmployee(Employee newData);
    public List displayAll();
    public List dispaySelectiveMultiple(String criteria);
    public List dispaySelectiveSingle(String criteria);
    public int insertDepartment(Department department);
    public int deleteDepartment(int departmentId);
    public boolean modifyDepartment(Department newData);
    public List<Department> displayAllDepartments();

}
