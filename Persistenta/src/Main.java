import com.mysql.cj.exceptions.ConnectionIsClosedException;
import jdk.javadoc.doclet.Taglet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private JPanel myPanel;
    static JComboBox cb1;
    static JList list1;
    static JScrollPane scroll;
    static DefaultListModel dlm;
    static JComboBox[] leftPar, columnName, operator, andOr, rightPar;
    static JButton submit, reset, cancel, addRow, deleteRow;
    static JTextField[] criteria;
    static JLabel l1, l2;
    private int maxRow = 10;
    private int count = -1;
    static String s, s1;
    static int i;

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

        DbEmployee employee = new DbEmployee();

        JButton connectDb = new JButton("ConnectDb");
        JButton closeDb = new JButton("CloseDb");
        JButton insertEmployee = new JButton("Insert employee");
        JButton updateEmployee = new JButton("Update employee");
        JButton deleteEmployee = new JButton("Delete employee");
        JButton showAllEmployees = new JButton("Show employees");
        JButton showCriteriaEmployees = new JButton("Show criteria employees");
        JButton clear = new JButton("Clear");


        this.add(scroll);
        this.add(connectDb);
        this.add(closeDb);
        this.add(insertEmployee);
        this.add(updateEmployee);
        this.add(deleteEmployee);
        this.add(showAllEmployees);
        this.add(showCriteriaEmployees);
        this.add(clear);

        connectDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DbEmployee.connect();

            }
        });

        closeDb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbEmployee.close();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                DbEmployee.close();
            }
        });

        insertEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee.insertEmployee();
            }
        });

        updateEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee.updateEmployee();
            }
        });

        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee.deleteEmployee();
            }
        });

        showAllEmployees.addActionListener(new

                                                   ActionListener() {
                                                       @Override
                                                       public void actionPerformed(ActionEvent e) {
                                                           try {
                                                               String s = "";
                                                               ArrayList<Employee> employees = employee.getAllEmployees("select * from employee");
                                                               for (Employee employee : employees) {
                                                                   s = s + employee.toString();
                                                               }

                                                               ta.setText(s);
                                                           } catch (ConnectionIsClosedException | NullPointerException exception) {
                                                               JOptionPane.showMessageDialog(null, exception.getMessage());
                                                           }
                                                       }
                                                   });

        showCriteriaEmployees.addActionListener(new

                                                        ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                JFrame f = new JFrame("Select employee for criteria");
                                                                JPanel p1 = new JPanel();
                                                                JPanel p2 = new JPanel();
                                                                JPanel p3 = new JPanel();
                                                                f.setSize(new Dimension(700, 500));
                                                                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                                f.setLayout(new BorderLayout(20, 20));

                                                                leftPar = new JComboBox[10];
                                                                columnName = new JComboBox[10];
                                                                operator = new JComboBox[10];
                                                                criteria = new JTextField[10];
                                                                rightPar = new JComboBox[10];
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
                                                                dlm.addElement("");
                                                                dlm.addElement("id");
                                                                dlm.addElement("name");
                                                                dlm.addElement("age");
                                                                dlm.addElement("address");
                                                                dlm.addElement("salary");
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
                                                                p2.setLayout(new GridLayout(0, 6, 20, 20));
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

                                                                submit.addActionListener(new

                                                                                                 ActionListener() {
                                                                                                     @Override
                                                                                                     public void actionPerformed(ActionEvent e) {
                                                                                                         s = "select ";
                                                                                                         DbEmployee employee1 = new DbEmployee();

                                                                                                         if (cb1.getSelectedItem() != "afisare") {


                                                                                                             s = s + cb1.getSelectedItem() + "(" + list1.getSelectedValue() + ")" + " from employee";
                                                                                                             s1 = s + " where ";

                                                                                                             if (count > -1) {

                                                                                                                 for (int i = 0; i <= count; i++) {

                                                                                                                     if (columnName[i].getSelectedItem() != "" && operator[i].getSelectedItem() != "" && criteria[i].getText() != "") {
                                                                                                                         s1 = s1 + ((leftPar[i].getSelectedItem() == "") ? "" : leftPar[i].getSelectedItem())
                                                                                                                                 + columnName[i].getSelectedItem()
                                                                                                                                 + ((operator[i].getSelectedItem() == "LIKE") ? " " : "")
                                                                                                                                 + operator[i].getSelectedItem()
                                                                                                                                 + ((columnName[i].getSelectedItem() == "name" || columnName[i].getSelectedItem() == "address") ? " '" : "")
                                                                                                                                 + ((operator[i].getSelectedItem() == "LIKE") ? "%" : "")
                                                                                                                                 + criteria[i].getText()
                                                                                                                                 + ((operator[i].getSelectedItem() == "LIKE") ? "%" : "")
                                                                                                                                 + ((columnName[i].getSelectedItem() == "name" || columnName[i].getSelectedItem() == "address") ? "'" : "")
                                                                                                                                 + ((rightPar[i].getSelectedItem() == "") ? "" : rightPar[i].getSelectedItem()) + " "
                                                                                                                                 + ((i == count) ? "" : ((andOr[i].getSelectedItem() == "") ? "" : andOr[i].getSelectedItem())) + " ";

                                                                                                                         if (i == count) {
                                                                                                                             try {
                                                                                                                                 ta.setText(String.valueOf(cb1.getSelectedItem()) + " " + String.valueOf(list1.getSelectedValue()) + ": " + String.valueOf(employee1.getCriteriaEmployees(s1)));

                                                                                                                             } catch (NullPointerException | ClassCastException exception) {
                                                                                                                                 JOptionPane.showMessageDialog(null, exception.getMessage());
                                                                                                                             }
                                                                                                                         }
                                                                                                                     } else {
                                                                                                                         JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields or delete condition rows", "Failed!!", JOptionPane.ERROR_MESSAGE);
                                                                                                                     }
                                                                                                                 }
                                                                                                             } else {

                                                                                                                 try {
                                                                                                                     ta.setText(String.valueOf(cb1.getSelectedItem()) + " " + String.valueOf(list1.getSelectedValue()) + ": " + String.valueOf(employee1.getCriteriaEmployees(s)));

                                                                                                                 } catch (ClassCastException exception) {
                                                                                                                     JOptionPane.showMessageDialog(null, exception.getMessage());
                                                                                                                 }
                                                                                                             }
                                                                                                         } else {

                                                                                                             int[] si = list1.getSelectedIndices();

                                                                                                             for (Integer i : si) {
                                                                                                                 s = s + list1.getModel().getElementAt(i) + ", ";
                                                                                                             }
                                                                                                             s = s.substring(0, s.length() - 2) + " from employee";
                                                                                                             s1 = s + " where ";
                                                                                                             if (count > -1) {

                                                                                                                 for (int i = 0; i <= count; i++) {

                                                                                                                     if (columnName[i].getSelectedItem() != "" && operator[i].getSelectedItem() != "" && criteria[i].getText() != "") {
                                                                                                                         s1 = s1 + ((leftPar[i].getSelectedItem() == "") ? "" : leftPar[i].getSelectedItem())
                                                                                                                                 + columnName[i].getSelectedItem()
                                                                                                                                 + ((operator[i].getSelectedItem() == "LIKE") ? " " : "")
                                                                                                                                 + operator[i].getSelectedItem()
                                                                                                                                 + ((columnName[i].getSelectedItem() == "name" || columnName[i].getSelectedItem() == "address") ? "'" : "")
                                                                                                                                 + ((operator[i].getSelectedItem() == "LIKE") ? "%" : "")
                                                                                                                                 + criteria[i].getText()
                                                                                                                                 + ((operator[i].getSelectedItem() == "LIKE") ? "%" : "")
                                                                                                                                 + ((columnName[i].getSelectedItem() == "name" || columnName[i].getSelectedItem() == "address") ? "'" : "")
                                                                                                                                 + ((rightPar[i].getSelectedItem() == "") ? "" : rightPar[i].getSelectedItem()) + " "
                                                                                                                                 + ((i == count) ? "" : ((andOr[i].getSelectedItem() == "") ? "" : andOr[i].getSelectedItem())) + " ";

                                                                                                                         if (i == count) {
                                                                                                                             try {
                                                                                                                                 String string = "";
                                                                                                                                 ArrayList<Employee> employees = employee1.getCriteriaEmployees1(s1);
                                                                                                                                 for (Employee employee : employees) {
                                                                                                                                     string = string + employee.toString();
                                                                                                                                 }
                                                                                                                                 andOr[i].setSelectedItem("");
                                                                                                                                 ta.setText(string);
                                                                                                                             } catch (NullPointerException exception) {
                                                                                                                                 JOptionPane.showMessageDialog(null, exception.getMessage());
                                                                                                                             }
                                                                                                                         }
                                                                                                                     } else {
                                                                                                                         JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields or delete condition rows", "Failed!!", JOptionPane.ERROR_MESSAGE);
                                                                                                                     }
                                                                                                                 }
                                                                                                             } else {
                                                                                                                 String string = "";
                                                                                                                 ArrayList<Employee> employees = employee1.getCriteriaEmployees1(s);
                                                                                                                 for (Employee employee : employees) {
                                                                                                                     string = string + employee.toString();
                                                                                                                 }
                                                                                                                 ta.setText(string);
                                                                                                             }

                                                                                                         }
                                                                                                         reset.doClick();
                                                                                                     }
                                                                                                 });

                                                                cancel.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        f.dispose();
                                                                    }
                                                                });

                                                                deleteRow.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (count > -1) {
                                                                            p2.remove(leftPar[count]);
                                                                            p2.remove(columnName[count]);
                                                                            p2.remove(operator[count]);
                                                                            p2.remove(criteria[count]);
                                                                            p2.remove(rightPar[count]);
                                                                            p2.remove(andOr[count]);
                                                                            count--;
                                                                            p2.revalidate();
                                                                            p2.repaint();
                                                                            f.pack();
                                                                        }
                                                                    }
                                                                });


                                                                reset.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        cb1.setSelectedItem("afisare");
                                                                        list1.setSelectedIndex(0);
                                                                        for (int i = count; i > -1; i--) {
                                                                            p2.remove(leftPar[i]);
                                                                            p2.remove(columnName[i]);
                                                                            p2.remove(operator[i]);
                                                                            p2.remove(criteria[i]);
                                                                            p2.remove(rightPar[i]);
                                                                            p2.remove(andOr[i]);
                                                                            p2.revalidate();
                                                                            p2.repaint();
                                                                            f.pack();
                                                                        }
                                                                        count = -1;
                                                                    }
                                                                });


                                                                addRow.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (count == maxRow - 1) {
                                                                            JOptionPane.showMessageDialog(null, "Maximum of 10 rows can be added", "Failed!!", JOptionPane.ERROR_MESSAGE);

                                                                            return;
                                                                        }
                                                                        count++;
                                                                        try {
                                                                            if (count > 0 && (andOr[count - 1].getSelectedItem() == "")) {

                                                                                JOptionPane.showMessageDialog(null, "Please select and/or for other condition!");
                                                                                count -= 1;
                                                                            } else {

                                                                                leftPar[count] = new JComboBox();
                                                                                leftPar[count].addItem("");
                                                                                leftPar[count].addItem("(");
                                                                                leftPar[count].setToolTipText("select left parantheses if necessary");

                                                                                columnName[count] = new JComboBox();
                                                                                columnName[count].addItem("");
                                                                                columnName[count].addItem("id");
                                                                                columnName[count].addItem("name");
                                                                                columnName[count].addItem("age");
                                                                                columnName[count].addItem("address");
                                                                                columnName[count].addItem("salary");
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

                                                                                rightPar[count] = new JComboBox();
                                                                                rightPar[count].addItem("");
                                                                                rightPar[count].addItem(")");
                                                                                rightPar[count].setToolTipText("select right parantheses if necessary");

                                                                                andOr[count] = new JComboBox();
                                                                                andOr[count].addItem("");
                                                                                andOr[count].addItem("and");
                                                                                andOr[count].addItem("or");
                                                                                andOr[count].setToolTipText("select and/or if other condition is necessary");
                                                                                p2.add(leftPar[count]);
                                                                                p2.add(columnName[count]);
                                                                                p2.add(operator[count]);
                                                                                p2.add(criteria[count]);
                                                                                p2.add(rightPar[count]);
                                                                                p2.add(andOr[count]);
                                                                                p2.revalidate();
                                                                                p2.repaint();
                                                                                f.pack();
                                                                            }

                                                                        } catch (NullPointerException exception) {
                                                                            System.out.println(exception.getMessage());
                                                                        }

                                                                    }
                                                                });
                                                                f.pack();
                                                                f.setVisible(true);
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

    public static void main(String[] args) throws
            SQLException, InvocationTargetException, InterruptedException {

        Runnable myGUI = new Runnable() {
            @Override
            public void run() {
//                DbEmployee employee = new DbEmployee();
//                employee.createTable();
                Main f = new Main("Database employee operation");

            }
        };
        SwingUtilities.invokeAndWait(myGUI);
    }
}
