package main;

import org.hibernate.QueryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class Main extends JFrame {

    private JPanel myPanel;
    private static JTextField id, name, age, address, salary;
    private static int option;
    private static JComboBox cb1;
    private static JComboBox<Department> departmentCb;
    private static JList list1;
    private static JScrollPane scroll;
    private static DefaultListModel dlm;
    private static JComboBox[] columnName, operator, andOr;
    private static JButton submit, reset, cancel, addRow, deleteRow;
    private static JButton insertDepartment, updateDepartment, deleteDepartment, displayAll, clear;
    private static JTextField[] criteria;
    private static JLabel l1, l2;
    private final int maxRow = 10;
    private static int count = -1;
    private static String s, s1;
    private static Employee employee;
    private static int employeeId, departmentId;
    private static List<Department> departmentList;
    private static List<Object[]> employeeObject;
    private static List employeeOb;

    public Main(String title) {

        super(title);
        this.setContentPane(myPanel);
        this.setSize(new Dimension(500, 300));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        JTextArea ta = new JTextArea(8, 40);

        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        scroll = new JScrollPane(ta);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        DbEmployee employeeDb = new DbEmployee();

        JButton connectDb = new JButton("ConnectDb");
        JButton closeDb = new JButton("CloseDb");
        JButton insertEmployee = new JButton("Insert employee");
        JButton updateEmployee = new JButton("Update employee");
        JButton deleteEmployee = new JButton("Delete employee");
        JButton showAllEmployees = new JButton("Show employees");
        JButton showCriteriaEmployees = new JButton("Show criteria employees");
        JButton department = new JButton("Department operation");
        clear = new JButton("Clear");


        this.add(scroll);
        this.add(connectDb);
        this.add(closeDb);
        this.add(insertEmployee);
        this.add(updateEmployee);
        this.add(deleteEmployee);
        this.add(showAllEmployees);
        this.add(showCriteriaEmployees);
        this.add(department);
        this.add(clear);

        connectDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen())) {
                    if (User.connect()) {
                        employeeDb.connectDb();
                    }
                } else if (DbEmployee.getSession().isOpen()) {
                    JOptionPane.showMessageDialog(null, "Connected to db");
                }
            }
        });

        closeDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {
                    JOptionPane.showMessageDialog(null, "Not connected to db");
                } else {
                    HibernateUtil.close();

                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {
                    System.exit(0);
                } else {
                    HibernateUtil.close();
                }

            }
        });

        insertEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                    JOptionPane.showMessageDialog(null, "Connect to db");

                } else {
                    name = new JTextField();
                    age = new JTextField();
                    address = new JTextField();
                    salary = new JTextField();
//                    dlm = new DefaultListModel();
//
//                    departmentList = employeeDb.displayAllDepartments();
//                    for (Department department : departmentList) {
//                        dlm.addElement(department);
//                    }
//
//                    list1 = new JList(dlm);

                    departmentCb = new JComboBox();
                    departmentList = employeeDb.displayAllDepartments();
                    departmentCb.addItem(null);
                    for (Department department : departmentList) {
                        departmentCb.addItem(department);
                    }


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
                            "Employee salary:", salary,
                            "Department:", departmentCb
                    };

                    option = JOptionPane.showConfirmDialog(null, message, "Insert employee", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {

                        if (!name.getText().isEmpty() && !age.getText().isEmpty() && !address.getText().isEmpty() && !salary.getText().isEmpty() && departmentCb.getSelectedItem() != null) {
                            employee = new Employee(name.getText(), Integer.parseInt(age.getText()), address.getText(), Double.parseDouble(salary.getText()), (Department) departmentCb.getSelectedItem());
                            employeeId = employeeDb.insertEmployee(employee);
                            JOptionPane.showMessageDialog(null, "Employee added successfully with id: " + employeeId);
                        } else {
                            JOptionPane.showMessageDialog(null, "Introduceti date despre angajat in toate campurile", "Atentionare", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }
        });

        updateEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                    JOptionPane.showMessageDialog(null, "Connect to db");

                } else {

                    id = new JTextField();
                    name = new JTextField();
                    age = new JTextField();
                    address = new JTextField();
                    salary = new JTextField();

                    departmentCb = new JComboBox();
                    departmentList = employeeDb.displayAllDepartments();
                    departmentCb.addItem(null);
                    for (Department department : departmentList) {
                        departmentCb.addItem(department);
                    }


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
                            "Employee salary:", salary,
                            "Department:", departmentCb
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
                        if (!id.getText().isEmpty() && (!name.getText().isEmpty() || !age.getText().isEmpty() || !address.getText().isEmpty() || !salary.getText().isEmpty() || departmentCb.getSelectedItem() != null)) {
                            Employee newData = new Employee();


                            newData.setId(Integer.parseInt(id.getText()));
                            if (!name.getText().isEmpty())
                                newData.setName(name.getText());
                            if (!age.getText().isEmpty())
                                newData.setAge(Integer.parseInt(age.getText()));
                            if (!address.getText().isEmpty())
                                newData.setAddress(address.getText());
                            if (!salary.getText().isEmpty())
                                newData.setSalary(Double.parseDouble(salary.getText()));
                            if (departmentCb.getSelectedItem() != "") {
                                Department department1 = (Department) departmentCb.getSelectedItem();
                                newData.setDepartment(department1);
                            }
                            boolean modif = employeeDb.modifyEmployee(newData);

                            if (modif)
                                JOptionPane.showMessageDialog(null, "ID: " + id.getText() + " was updated successfully");

                            else {
                                JOptionPane.showMessageDialog(null, "Employee id doesn't exist in db. Try an existent id.", "Warning", JOptionPane.ERROR_MESSAGE);
                            }


                        } else if (id.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Introduceti id angajat", "Atentionare", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please insert data for update", "Insert data", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                    JOptionPane.showMessageDialog(null, "Connect to db");

                } else {

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

                            employeeId = employeeDb.deleteEmployee(Integer.parseInt(id.getText()));

                            if (employeeId != 0)
                                JOptionPane.showMessageDialog(null, "Employee deleted successfully");
                            else
                                JOptionPane.showMessageDialog(null, "Employee id doesn't exist in db. Try an existent id.", "Warning", JOptionPane.ERROR_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Introduceti id angajat", "Atentionare", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

        });

        showAllEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {
                    JOptionPane.showMessageDialog(null, "Connect to db");
                } else {
                    String s = "";
                    employeeObject = employeeDb.displayAll();
                    for (Object[] ob : employeeObject) {
                        for (int i = 0; i < ob.length; i++) {
                            switch (i) {
                                case 0:
                                    s = s + "id: " + ob[i] + " ";
                                    break;
                                case 1:
                                    s = s + "name: " + ob[i] + " ";
                                    break;
                                case 2:
                                    s = s + "age: " + ob[i] + " ";
                                    break;
                                case 3:
                                    s = s + "address: " + ob[i] + " ";
                                    break;
                                case 4:
                                    s = s + "salary: " + ob[i] + " ";
                                    break;
                                case 5:
                                    s = s + "department: " + ob[i] + "\n";
                                    break;
                            }
                        }
                    }
                    ta.setText(s);
                }
            }
        });

        showCriteriaEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {
                    JOptionPane.showMessageDialog(null, "Connect to db");
                } else {
                    JFrame f = new JFrame("Select employee for criteria");
                    JPanel p1 = new JPanel();
                    JPanel p2 = new JPanel();
                    JPanel p3 = new JPanel();
                    f.setSize(new Dimension(700, 500));
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.setLayout(new BorderLayout(20, 20));


                    columnName = new JComboBox[10];
                    operator = new JComboBox[10];
                    criteria = new JTextField[10];
                    andOr = new JComboBox[10];

                    l1 = new JLabel("Selectati afisare/optiuni calcul: ");

                    cb1 = new JComboBox();
                    cb1.addItem("afisare");
                    cb1.addItem("sum");
                    cb1.addItem("count");
                    cb1.addItem("avg");
                    cb1.addItem("min");
                    cb1.addItem("max");

                    l2 = new JLabel("Selectati criterii afisare/calcul: ");

                    dlm = new DefaultListModel();

                    dlm.addElement("id");
                    dlm.addElement("name");
                    dlm.addElement("age");
                    dlm.addElement("address");
                    dlm.addElement("salary");
                    dlm.addElement("departmentId");
                    dlm.addElement("department");
                    list1 = new JList(dlm);


                    submit = new JButton("Submit");
                    reset = new JButton("Reset");
                    cancel = new JButton("Cancel");
                    addRow = new JButton("New Condition");
                    deleteRow = new JButton("Delete Condition");

                    f.add(p1, BorderLayout.PAGE_START);
                    f.add(p2, BorderLayout.CENTER);
                    f.add(p3, BorderLayout.PAGE_END);
                    p1.add(l1);
                    p1.add(cb1);
                    p1.add(l2);
                    p1.add(list1);
                    p1.setLayout(new GridLayout(0, 6, 20, 20));

                    p1.add(addRow);
                    p1.add(deleteRow);
                    p3.add(submit);
                    p3.add(reset);
                    p3.add(cancel);
                    p2.setLayout(new GridLayout(0, 4, 20, 20));
                    p2.setSize(new Dimension(700, 50));
                    p3.setLayout(new GridLayout(0, 3, 20, 20));
                    p3.setSize(new Dimension(700, 50));

                    cb1.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (cb1.getSelectedItem() != "afisare")
                                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            else
                                list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }
                    });

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                s = "select ";
                                if (cb1.getSelectedItem() != "afisare") {
                                    s = s + cb1.getSelectedItem() + "(e." + list1.getSelectedValue() + ") from Employee e";
                                    System.out.println(s);
                                    if (count > -1) {
                                        boolean cond = false;
                                        for (int j = 0; j <= count; j++) {
                                            if ((columnName[j].getSelectedItem() == "departmentId") || (columnName[j].getSelectedItem() == "department"))
                                                cond = true;
                                        }

                                        if (cond)
                                            s1 = s + " left join e.department d where ";
                                        else
                                            s1 = s + " where ";

                                        for (int i = 0; i <= count; i++) {
                                            if (columnName[i].getSelectedItem() != "" && operator[i].getSelectedItem() != "" && !criteria[i].getText().isEmpty()) {
                                                s1 = s1 + ((columnName[i].getSelectedItem() == "departmentId" || columnName[i].getSelectedItem() == "department") ? "d." : "e.") + (columnName[i].getSelectedItem() == "department" ? "name" : columnName[i].getSelectedItem())
                                                        + ((operator[i].getSelectedItem() == "LIKE") ? " " : "")
                                                        + operator[i].getSelectedItem()
                                                        + ((operator[i].getSelectedItem() == "LIKE") ? " '%" : "")
                                                        + criteria[i].getText()
                                                        + ((operator[i].getSelectedItem() == "LIKE") ? "%'" : "")
                                                        + " "
                                                        + ((i == count) ? "" : ((andOr[i].getSelectedItem() == "") ? "" : andOr[i].getSelectedItem())) + " ";

                                                if (i == count) {
                                                    employeeOb = employeeDb.dispaySelectiveSingle(s1);
                                                    ta.setText(cb1.getSelectedItem() + " " + list1.getSelectedValue() + ": " + employeeOb.get(0));
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields or delete condition rows", "Failed!!", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    } else {
                                        ta.setText(cb1.getSelectedItem() + " " + list1.getSelectedValue() + ": " + employeeDb.dispaySelectiveSingle(s).get(0));
                                    }
                                } else {

                                    int[] si = list1.getSelectedIndices();
                                    boolean dep = false;
                                    for (int in : si) {
                                        s = s + ((list1.getModel().getElementAt(in) == "department" || list1.getModel().getElementAt(in) == "departmentId") ? ("d." + (list1.getModel().getElementAt(in) == "department" ? "name" : list1.getModel().getElementAt(in))) : ("e." + list1.getModel().getElementAt(in))) + ", ";
                                        if (list1.getModel().getElementAt(in) == "department" || list1.getModel().getElementAt(in) == "departmentId")
                                            dep = true;
                                    }
                                    if (dep)
                                        s = s.substring(0, s.length() - 2) + " from Employee e left join e.department d";
                                    else
                                        s = s.substring(0, s.length() - 2) + " from Employee e";


                                    if (count > -1) {
                                        boolean cond = false;
                                        for (int j = 0; j <= count; j++) {
                                            if ((columnName[j].getSelectedItem() == "departmentId") || (columnName[j].getSelectedItem() == "department"))
                                                cond = true;
                                        }

                                        if (!dep && cond)
                                            s1 = s + " left join e.department d where ";
                                        else
                                            s1 = s + " where ";

                                        for (int i = 0; i <= count; i++) {
                                            if (columnName[i].getSelectedItem() != "" && operator[i].getSelectedItem() != "" && !criteria[i].getText().isEmpty()) {

                                                s1 = s1 + ((columnName[i].getSelectedItem() == "departmentId" || columnName[i].getSelectedItem() == "department") ? "d." : "e.") + (columnName[i].getSelectedItem() == "department" ? "name" : columnName[i].getSelectedItem())
                                                        + ((operator[i].getSelectedItem() == "LIKE") ? " " : "")
                                                        + operator[i].getSelectedItem()
                                                        + ((operator[i].getSelectedItem() == "LIKE") ? " '%" : "")
                                                        + criteria[i].getText()
                                                        + ((operator[i].getSelectedItem() == "LIKE") ? "%'" : "")
                                                        + " "
                                                        + ((i == count) ? "" : ((andOr[i].getSelectedItem() == "") ? "" : andOr[i].getSelectedItem())) + " ";
                                                System.out.println(s);
                                                System.out.println(s1);
                                                if (i == count) {
                                                    String string = "";
                                                    int j = 0;

                                                    if (si.length == 1) {
                                                        employeeOb = employeeDb.dispaySelectiveSingle(s1);
                                                        for (int k = 0; k < employeeOb.size(); k++) {
                                                            string = string + list1.getSelectedValue() + ":" + employeeOb.get(k) + "\n";
                                                        }
                                                    } else {
                                                        employeeObject = employeeDb.dispaySelectiveMultiple(s1);

                                                        for (Object[] os : employeeObject) {
                                                            for (Object o : os) {
                                                                string = string + list1.getModel().getElementAt(si[j]) + ":" + o + " ";
                                                                j++;
                                                                if (os.length == j) {
                                                                    string = string + "\n";
                                                                    j = 0;
                                                                }
                                                            }
                                                        }
                                                    }

                                                    andOr[i].setSelectedItem("");
                                                    ta.setText(string);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields or delete condition rows", "Failed!!", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    } else {
                                        System.out.println(s);
                                        System.out.println(s1);
                                        String string = "";
                                        if (si.length == 1) {
                                            employeeOb = employeeDb.dispaySelectiveSingle(s);
                                            for (int k = 0; k < employeeOb.size(); k++) {
                                                string = string + list1.getSelectedValue() + ":" + employeeOb.get(k) + "\n";
                                            }
                                        } else {
                                            employeeObject = employeeDb.dispaySelectiveMultiple(s);
                                            int j = 0;
                                            for (Object[] os : employeeObject) {
                                                for (Object o : os) {
                                                    string = string + list1.getModel().getElementAt(si[j]) + ":" + o + " ";
                                                    j++;
                                                    if (os.length == j) {
                                                        string = string + "\n";
                                                        j = 0;
                                                    }
                                                }
                                            }
                                        }

                                        ta.setText(string);
                                    }
                                }
                                reset.doClick();
                            } catch (QueryException | IllegalArgumentException exception) {
                                JOptionPane.showMessageDialog(null, "Is not a valid query");
                            }
                        }
                    });

                    cancel.addActionListener(new

                                                     ActionListener() {
                                                         @Override
                                                         public void actionPerformed(ActionEvent e) {
                                                             f.dispose();
                                                         }
                                                     });

                    deleteRow.addActionListener(new

                                                        ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (count > -1) {
                                                                    p2.remove(columnName[count]);
                                                                    p2.remove(operator[count]);
                                                                    p2.remove(criteria[count]);
                                                                    p2.remove(andOr[count]);
                                                                    count--;
                                                                    p2.revalidate();
                                                                    p2.repaint();
                                                                    f.pack();
                                                                }
                                                            }
                                                        });


                    reset.addActionListener(new

                                                    ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            cb1.setSelectedItem("afisare");
                                                            list1.setSelectedIndex(0);
                                                            for (int i = count; i > -1; i--) {

                                                                p2.remove(columnName[i]);
                                                                p2.remove(operator[i]);
                                                                p2.remove(criteria[i]);
                                                                p2.remove(andOr[i]);
                                                                p2.revalidate();
                                                                p2.repaint();
                                                                f.pack();
                                                            }
                                                            count = -1;
                                                        }
                                                    });


                    addRow.addActionListener(new

                                                     ActionListener() {
                                                         @Override
                                                         public void actionPerformed(ActionEvent e) {
                                                             if (count == maxRow - 1) {
                                                                 JOptionPane.showMessageDialog(null, "Maximum of 10 rows can be added", "Failed!!", JOptionPane.ERROR_MESSAGE);

                                                                 return;
                                                             }
                                                             count++;

                                                             if (count > 0 && (andOr[count - 1].getSelectedItem() == "")) {

                                                                 JOptionPane.showMessageDialog(null, "Please select and/or for other condition!");
                                                                 count -= 1;
                                                             } else {

                                                                 columnName[count] = new JComboBox();
                                                                 columnName[count].addItem("");
                                                                 columnName[count].addItem("id");
                                                                 columnName[count].addItem("name");
                                                                 columnName[count].addItem("age");
                                                                 columnName[count].addItem("address");
                                                                 columnName[count].addItem("salary");
                                                                 columnName[count].addItem("departmentId");
                                                                 columnName[count].addItem("department");
                                                                 columnName[count].setToolTipText("select column name for condition");

                                                                 operator[count] = new JComboBox();
                                                                 operator[count].addItem("");
                                                                 operator[count].addItem("=");
                                                                 operator[count].addItem("<=");
                                                                 operator[count].addItem(">=");
                                                                 operator[count].addItem("!=");
                                                                 operator[count].addItem(">");
                                                                 operator[count].addItem("<");
                                                                 operator[count].addItem("LIKE");
                                                                 operator[count].setToolTipText("select operator for condition");

                                                                 criteria[count] = new JTextField(10);
                                                                 criteria[count].setToolTipText("insert condition");

                                                                 andOr[count] = new JComboBox();
                                                                 andOr[count].addItem("");
                                                                 andOr[count].addItem("and");
                                                                 andOr[count].addItem("or");
                                                                 andOr[count].setToolTipText("select and/or if other condition is necessary");

                                                                 p2.add(columnName[count]);
                                                                 p2.add(operator[count]);
                                                                 p2.add(criteria[count]);
                                                                 p2.add(andOr[count]);
                                                                 p2.revalidate();
                                                                 p2.repaint();
                                                                 f.pack();
                                                             }
                                                         }
                                                     });
                    f.pack();
                    f.setVisible(true);
                }
            }
        });

        department.addActionListener(new

                                             ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {
                                                     if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                                                         JOptionPane.showMessageDialog(null, "Connect to db");

                                                     } else {

                                                         JFrame f = new JFrame("Departament operation");
                                                         f.setSize(new Dimension(500, 300));
                                                         f.setLocationRelativeTo(null);
                                                         f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                         f.setLayout(new FlowLayout());
                                                         JTextArea ta = new JTextArea(8, 40);
                                                         ta.setWrapStyleWord(true);
                                                         ta.setLineWrap(true);
                                                         scroll = new JScrollPane(ta);
                                                         scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                                                         insertDepartment = new JButton("Insert Department");
                                                         updateDepartment = new JButton("Update Department");
                                                         deleteDepartment = new JButton("Delete Department");
                                                         displayAll = new JButton("Display all departments");
                                                         clear = new JButton("Clear");

                                                         f.add(scroll);
                                                         f.add(insertDepartment);
                                                         f.add(updateDepartment);
                                                         f.add(deleteDepartment);
                                                         f.add(displayAll);
                                                         f.add(clear);
                                                         f.setVisible(true);

                                                         insertDepartment.addActionListener(new ActionListener() {
                                                             @Override
                                                             public void actionPerformed(ActionEvent e) {

                                                                 if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                                                                     JOptionPane.showMessageDialog(null, "Connect to db");

                                                                 } else {
                                                                     name = new JTextField();

                                                                     name.addKeyListener(new KeyAdapter() {
                                                                         @Override
                                                                         public void keyTyped(KeyEvent e) {
                                                                             char c = e.getKeyChar();
                                                                             if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))
                                                                                 e.consume();
                                                                         }
                                                                     });

                                                                     Object[] message = {
                                                                             "Department name:", name
                                                                     };

                                                                     option = JOptionPane.showConfirmDialog(null, message, "Insert department", JOptionPane.OK_CANCEL_OPTION);

                                                                     if (option == JOptionPane.OK_OPTION) {

                                                                         if (!name.getText().isEmpty()) {
                                                                             Department department = new Department(name.getText());
                                                                             departmentId = employeeDb.insertDepartment(department);
                                                                             JOptionPane.showMessageDialog(null, "Department added successfully with id: " + departmentId);
                                                                         } else {
                                                                             JOptionPane.showMessageDialog(null, "Insert department name", "Atentionare", JOptionPane.ERROR_MESSAGE);
                                                                         }
                                                                     } else {
                                                                         JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                                                                     }

                                                                 }
                                                             }
                                                         });

                                                         updateDepartment.addActionListener(new ActionListener() {
                                                             @Override
                                                             public void actionPerformed(ActionEvent e) {
                                                                 if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                                                                     JOptionPane.showMessageDialog(null, "Connect to db");

                                                                 } else {

                                                                     id = new JTextField();
                                                                     name = new JTextField();

                                                                     name.addKeyListener(new KeyAdapter() {
                                                                         @Override
                                                                         public void keyTyped(KeyEvent e) {
                                                                             char c = e.getKeyChar();
                                                                             if (!(Character.isAlphabetic(c) || Character.isWhitespace(c)))
                                                                                 e.consume();
                                                                         }
                                                                     });

                                                                     Object[] message = {
                                                                             "Department id:", id,
                                                                             "Department name:", name
                                                                     };

                                                                     id.addKeyListener(new KeyAdapter() {
                                                                         @Override
                                                                         public void keyTyped(KeyEvent e) {
                                                                             char c = e.getKeyChar();
                                                                             if (!(Character.isDigit(c)))
                                                                                 e.consume();
                                                                         }
                                                                     });

                                                                     option = JOptionPane.showConfirmDialog(null, message, "Update department", JOptionPane.OK_CANCEL_OPTION);

                                                                     if (option == JOptionPane.OK_OPTION) {
                                                                         if (!id.getText().isEmpty() && !name.getText().isEmpty()) {
                                                                             Department newData = new Department(Integer.parseInt(id.getText()), name.getText());
                                                                             boolean modif = employeeDb.modifyDepartment(newData);

                                                                             if (modif)
                                                                                 JOptionPane.showMessageDialog(null, "ID: " + id.getText() + " was updated successfully");

                                                                             else {
                                                                                 JOptionPane.showMessageDialog(null, "Department id doesn't exist in db. Try an existent id.", "Warning", JOptionPane.ERROR_MESSAGE);
                                                                             }


                                                                         } else if (id.getText().isEmpty()) {
                                                                             JOptionPane.showMessageDialog(null, "Insert department id", "Atentionare", JOptionPane.ERROR_MESSAGE);
                                                                         } else {
                                                                             JOptionPane.showMessageDialog(null, "Please insert data for update", "Insert data", JOptionPane.ERROR_MESSAGE);
                                                                         }
                                                                     } else {
                                                                         JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                                                                     }
                                                                 }
                                                             }
                                                         });

                                                         deleteDepartment.addActionListener(new ActionListener() {
                                                             @Override
                                                             public void actionPerformed(ActionEvent e) {


                                                                 if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {

                                                                     JOptionPane.showMessageDialog(null, "Connect to db");

                                                                 } else {

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
                                                                             "Department id:", id
                                                                     };

                                                                     option = JOptionPane.showConfirmDialog(null, message, "Select department id", JOptionPane.OK_CANCEL_OPTION);

                                                                     if (option == JOptionPane.OK_OPTION) {
                                                                         if (!id.getText().isEmpty()) {

                                                                             departmentId = employeeDb.deleteDepartment(Integer.parseInt(id.getText()));

                                                                             if (departmentId != 0)
                                                                                 JOptionPane.showMessageDialog(null, "Department deleted successfully! Modify department for employees assigned to this department because they aren't assigned to any department now. ");
                                                                             else
                                                                                 JOptionPane.showMessageDialog(null, "Department id doesn't exist in db. Try an existent id.", "Warning", JOptionPane.ERROR_MESSAGE);

                                                                         } else {
                                                                             JOptionPane.showMessageDialog(null, "Insert department id", "Atentionare", JOptionPane.ERROR_MESSAGE);
                                                                         }
                                                                     } else {
                                                                         JOptionPane.showMessageDialog(null, "Connection canceled", "Connection canceled", JOptionPane.INFORMATION_MESSAGE);
                                                                     }
                                                                 }
                                                             }
                                                         });

                                                         displayAll.addActionListener(new ActionListener() {
                                                             @Override
                                                             public void actionPerformed(ActionEvent e) {
                                                                 if (DbEmployee.verifyConnexion() ? DbEmployee.verifyConnexion() : !DbEmployee.getSession().isOpen()) {
                                                                     JOptionPane.showMessageDialog(null, "Connect to db");
                                                                 } else {
                                                                     String s = "";
                                                                     departmentList = employeeDb.displayAllDepartments();
                                                                     for (Department department : departmentList) {
                                                                         s = s + department.toString();
                                                                     }
                                                                     ta.setText(s);
                                                                 }
                                                             }
                                                         });


                                                         clear.addActionListener(new ActionListener() {
                                                             @Override
                                                             public void actionPerformed(ActionEvent e) {
                                                                 ta.setText("");
                                                             }
                                                         });
                                                     }
                                                 }
                                             });


        clear.addActionListener(new

                                        ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                ta.setText("");
                                            }
                                        });

        this.

                setVisible(true);
    }


    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

        Runnable myGUI = new Runnable() {
            @Override
            public void run() {
                Main f = new Main("Database employee operation");
            }
        };
        SwingUtilities.invokeAndWait(myGUI);
    }
}
