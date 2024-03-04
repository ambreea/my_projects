import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;


public class DbEmployee implements Operations {

    static Connection connection = null;
    static JTextField url, username, password;
    static JTextField id, name, age, address, salary;
    static int option;

    static void connect() {
        url = new JTextField();
        username = new JTextField();
        password = new JPasswordField();
        Object[] message = {
                "URL:", url,
                "Username:", username,
                "Password:", password
        };

        option = JOptionPane.showConfirmDialog(null, message, "Connect to db", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                url.setText("jdbc:mysql://localhost/test");
                username.setText("root");
                password.setText("Andree@29");
                connection = DriverManager.getConnection(url.getText(), username.getText(), password.getText());
                if (!connection.isClosed()) {
                    JOptionPane.showMessageDialog(null, "Connection is established");
                } else {
                    JOptionPane.showMessageDialog(null, "Connection is not established");
                }
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null, "Error in database connection:\n" + exception.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Connection canceled");
        }
    }


    static void close() {
        try {
            connection.close();
            JOptionPane.showMessageDialog(null, "Closed...");
        } catch (SQLException | NullPointerException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void createTable() {
        try {
            connect();
            Statement st = connection.createStatement();
            st.execute("CREATE TABLE test.employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45), age INT, address VARCHAR(80), salary DOUBLE(16,2))");
            st.close();
            close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    @Override
    public void insertEmployee() {

        try {

            if (connection == null || connection.isClosed())
                JOptionPane.showMessageDialog(null, "Connect to db");
            else {

                name = new JTextField();
                age = new JTextField();
                address = new JTextField();
                salary = new JTextField();
                name.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))
                            e.consume();
                    }
                });
                age.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isDigit(c)))
                            e.consume();
                    }
                });
                salary.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isDigit(c)))
                            e.consume();
                    }
                });
                Object[] message = {
                        "Employee name:", name,
                        "Employee age:", age,
                        "Employee address:", address,
                        "Employee salary:", salary
                };

                option = JOptionPane.showConfirmDialog(null, message, "Insert employee", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {

                    if (!name.getText().isEmpty() && !age.getText().isEmpty() && !address.getText().isEmpty() && !salary.getText().isEmpty()) {
                        Employee employee = new Employee(name.getText(), Integer.parseInt(age.getText()), address.getText(), Double.parseDouble(salary.getText()));
//                connect();
                        try {
                            PreparedStatement ps = connection.prepareStatement("insert into employee values (null,?,?,?,?)");
                            ps.setString(1, employee.getName());
                            ps.setString(2, String.valueOf(employee.getAge()));
                            ps.setString(3, employee.getAddress());
                            ps.setString(4, String.valueOf(employee.getSalary()));
                            ps.execute();
                            JOptionPane.showMessageDialog(null, "Employee added successfully");
                            ps.close();
                        } catch (SQLException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage());
                        }

//                close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Introduceti date despre angajat in toate campurile", "Atentionare", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        } catch (SQLException | NullPointerException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }


    @Override
    public void deleteEmployee() {

        try {

            if (connection == null || connection.isClosed())
                JOptionPane.showMessageDialog(null, "Connect to db");
            else {
                //        connect();
                id = new JTextField();

                id.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isDigit(c)))
                            e.consume();
                    }
                });

                Object[] message = {
                        "Employee id:", id
                };

                option = JOptionPane.showConfirmDialog(null, message, "Select employee id", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    if (!id.getText().isEmpty()) {

                        try {
                            Statement st = connection.createStatement();
                            int i = st.executeUpdate("delete from employee where id=" + id.getText());
                            if (i != 0)
                                JOptionPane.showMessageDialog(null, "Employee deleted successfully");
                            else
                                JOptionPane.showMessageDialog(null, "Employee id doesn't exist in db. Try an existent id.", "Warning", JOptionPane.ERROR_MESSAGE);
                            st.close();
                        } catch (SQLException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Introduceti id angajat", "Atentionare", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                }
//        close();
            }
        } catch (SQLException | NullPointerException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }


    @Override
    public void updateEmployee() {

        //connect();

        id = new JTextField();
        name = new JTextField();
        age = new JTextField();
        address = new JTextField();
        salary = new JTextField();
        String s = "";

        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))
                    e.consume();
            }
        });

        age.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)))
                    e.consume();
            }
        });
        salary.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
            }
        });

        Object[] message = {
                "Employee id:", id,
                "Employee name:", name,
                "Employee age:", age,
                "Employee address:", address,
                "Employee salary:", salary
        };

        id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)))
                    e.consume();
            }
        });

        option = JOptionPane.showConfirmDialog(null, message, "Select employee id", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            if (!id.getText().isEmpty() && (!name.getText().isEmpty() || !age.getText().isEmpty() || !address.getText().isEmpty() || !salary.getText().isEmpty())) {

                try {

                    Statement st = connection.createStatement();
                    s = s + "update employee set ";

                    if (!name.getText().isEmpty())
                        s = s + "name='" + name.getText() + "', ";
                    if (!age.getText().isEmpty())
                        s = s + "age=" + age.getText() + ", ";
                    if (!address.getText().isEmpty())
                        s = s + "address='" + address.getText() + "', ";
                    if (!salary.getText().isEmpty())
                        s = s + "salary=" + salary.getText();
                    if (s.endsWith(", "))
                        s = s.substring(0, s.length() - 2);
                    s = s + " where id=" + id.getText();

                    int i = st.executeUpdate(s);
                    JOptionPane.showMessageDialog(null, "ID: " + id.getText() + " was updated successfully");

                    if (i == 0) {
                        JOptionPane.showMessageDialog(null, "Employee id doesn't exist in db. Try an existent id.", "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                    st.close();
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }

            } else if (id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Introduceti id angajat", "Atentionare", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please insert data for update", "Insert data", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
        }
        //close();
    }


    @Override
    public ArrayList<Employee> getAllEmployees(String query) {

        ArrayList<Employee> list = new ArrayList<>();
        try {
            Statement st = null;
            st = connection.createStatement();
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5));
                list.add(employee);
            }
            st.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        return list;
    }

    @Override
    public int getCriteriaEmployees(String query) {
        int n = 0;

        try {
            Statement st = null;
            st = connection.createStatement();
            st.executeQuery(query);

            ResultSet rs = st.getResultSet();
            while (rs.next())
                n = ((Number) rs.getObject(1)).intValue();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        return n;
    }

    @Override
    public ArrayList<Employee> getCriteriaEmployees1(String query) {
        ArrayList<Employee> list = new ArrayList<>();


        try {
            Statement st = null;
            st = connection.createStatement();
            st.executeQuery(query);

            ResultSet rs = st.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNumber = rsmd.getColumnCount();

            int id = 0, age = 0;
            double salary = 0;
            String name = null, address = null;

            while (rs.next()) {
                for (int i = 1; i <= columnNumber; i++) {
                    String columnName = rsmd.getColumnName(i);

                    if (columnName.equals("id"))
                        id = rs.getInt("id");

                    if (columnName.equals("name"))
                        name = rs.getString("name");

                    if (columnName.equals("age"))
                        age = rs.getInt("age");

                    if (columnName.equals("address"))
                        address = rs.getString("address");

                    if (columnName.equals("salary"))
                        salary = rs.getDouble("salary");

                }
                Employee employee = new Employee(id, name, age, address, salary);
                list.add(employee);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        return list;
    }
}
