import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Calculator extends Frame {

    public Calculator(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image i = toolkit.getImage("calculation.jpg");

        AffineTransform t = new AffineTransform();
        t.translate(200, 10);
        t.scale(0.35, 0.35);
        g2.setTransform(t);
        g2.drawImage(i, 400, 50, this);
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args) {

        Calculator f = new Calculator("mini AWT calculator");
        f.setVisible(true);
        f.setSize(600, 300);
        f.setLayout(null);

        Font font = new Font("Arial", Font.PLAIN, 13);

        Label l1 = new Label("Enter first number: ");
        l1.setBounds(50, 50, 145, 30);
        l1.setFont(font);

        Label l2 = new Label("Choose operation: ");
        l2.setBounds(50, 100, 145, 30);
        l2.setFont(font);

        Label l3 = new Label("Enter second number: ");
        l3.setBounds(50, 150, 145, 30);
        l3.setFont(font);

        Button clear = new Button("Clear");
        clear.setBounds(50, 200, 145, 30);
        clear.setFont(font);

        Label l5 = new Label("Result is: ");
        l5.setBounds(50, 250, 145, 30);
        l5.setFont(font);
        l5.setBackground(Color.pink);

        TextField tf1 = new TextField();
        tf1.setBounds(200, 50, 150, 30);
        tf1.setFont(font);
        tf1.setBackground(Color.lightGray);

        Choice choice = new Choice();
        choice.add("+");
        choice.add("-");
        choice.add("/");
        choice.add("*");
        choice.setBounds(200, 100, 150, 30);
        choice.setBackground(Color.pink);

        TextField tf2 = new TextField();
        tf2.setBounds(200, 150, 150, 30);
        tf2.setFont(font);
        tf2.setBackground(Color.lightGray);

        Button b = new Button("Calculate");
        b.setFont(font);
        b.setBounds(200, 200, 145, 30);

        TextField tf3 = new TextField();
        tf3.setBounds(200, 250, 150, 30);
        tf3.setFont(font);
        tf3.setBackground(Color.lightGray);

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(clear);
        f.add(l5);

        f.add(tf1);
        f.add(choice);
        f.add(tf2);
        f.add(b);
        f.add(tf3);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double i = 0;
                double j = 0;

                if (!isNumeric(tf1.getText()) || !isNumeric(tf2.getText())) {
                    tf1.setText("Introduceti numar");
                    tf2.setText("Introduceti numar");
                    tf3.setText("err");
                } else {
                    i = Double.parseDouble(tf1.getText());
                    j = Double.parseDouble(tf2.getText());

                    if (choice.getSelectedItem() == "+") {
                        tf3.setText(String.valueOf(i + j));
                    } else if (choice.getSelectedItem() == "-") {
                        tf3.setText(String.valueOf(i - j));
                    } else if (choice.getSelectedItem() == "*" && (i * j == -0.0)) {
                        tf3.setText("0.0");
                    } else if (choice.getSelectedItem() == "*") {
                        tf3.setText(String.valueOf(i * j));
                    } else if (choice.getSelectedItem() == "/") {
                        String s = String.valueOf(i / j);
                        if (s == "-Infinity" || s == "Infinity" || s == "NaN") {

                            tf2.setText("Introduceti numar != 0");
                            tf3.setText("err div 0");
                        } else if (i / j == -0.0) {
                            tf3.setText("0.0");
                        } else {
                            tf3.setText(String.valueOf(i / j));
                        }
                    }
                }
            }
        });


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

