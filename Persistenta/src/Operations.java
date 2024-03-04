import java.sql.SQLException;
import java.util.ArrayList;


public interface Operations {
    public abstract void createTable();
    public abstract void insertEmployee();
    public abstract void deleteEmployee();
    public abstract void updateEmployee();
    public abstract ArrayList<Employee> getAllEmployees(String query);
    public abstract ArrayList<Employee> getCriteriaEmployees1(String query);
    public abstract int getCriteriaEmployees(String query);
}
