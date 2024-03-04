import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.time.*;

public class MyComponent extends JComponent {

    JRadioButton rb1, rb2;
    JFormattedTextField tf1, tf2;
    JButton btn1, btn2, btn3;
    JLabel label1, label2;
    JComboBox cb;
    Color c;
    static long elapsed, i;
    LocalTime time, time1;
    Duration d;


    public MyComponent() throws ParseException, BadLocationException {

        JFrame frame = new JFrame("Changing color");
        frame.setSize(new Dimension(500, 380));
        frame.setLayout(new FlowLayout());

        ButtonGroup gl = new ButtonGroup();
        rb1 = new JRadioButton("on time");
        rb2 = new JRadioButton("countdown (sec): ");
        gl.add(rb1);
        gl.add(rb2);
        rb1.setPreferredSize(new Dimension(100, 20));
        rb1.setForeground(new Color(-3395476));
        rb2.setPreferredSize(new Dimension(100, 20));
        rb2.setForeground(new Color(-3395476));


        tf1 = new JFormattedTextField(new MaskFormatter("##:##:##"));
        tf1.setPreferredSize(new Dimension(100, 20));
        tf1.setForeground(new Color(-3395476));

        tf1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (Integer.parseInt(tf1.getText().substring(0, 2)) > 23 || Integer.parseInt(tf1.getText().substring(3, 5)) > 59 || Integer.parseInt(tf1.getText().substring(6, 8)) > 59) {
                        JOptionPane.showMessageDialog(null, "Introduceti ora valida");
                        tf1.setValue(null);
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Nu ati introdus un numar");
                }
            }
        });

        tf1.setEditable(false);

        tf2 = new JFormattedTextField();
        tf2.setPreferredSize(new Dimension(100, 20));
        tf2.setForeground(new Color(-3395476));
        tf2.setEditable(false);

        tf2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)))
                    e.consume();
            }
        });

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb1.isSelected()) {
                    tf1.setEditable(true);
                    tf2.setEditable(false);
                    tf2.setText("");

                } else if (rb2.isSelected()) {
                    tf1.setEditable(false);
                    tf1.setValue(null);
                    tf2.setEditable(true);
                }
            }
        };

        rb1.addActionListener(al);
        rb2.addActionListener(al);

        JPanel p1 = new JPanel();
        p1.add(rb1);
        p1.add(tf1);
        p1.add(rb2);
        p1.add(tf2);
        p1.setLayout(new GridLayout(2, 2, 10, 10));
        p1.setSize(new Dimension(400, 70));
        p1.setVisible(true);
        p1.setLocation(50, 50);

        btn1 = new JButton("Choose color");
        btn1.setPreferredSize(new Dimension(135, 25));
        btn1.setForeground(new Color(-3395476));
        label1 = new JLabel("No color selected");
        label1.setPreferredSize(new Dimension(120, 25));
        label1.setForeground(new Color(-3395476));
        JPanel p2 = new JPanel();
        p2.add(btn1);
        p2.add(label1);
        p2.setLayout(new FlowLayout());
        p2.setSize(new Dimension(300, 35));
        p2.setVisible(true);
        p2.setLocation(100, 150);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser jcc = new JColorChooser();
                c = jcc.showDialog(null, "Choose color", null);
                if (c != null)
                    label1.setText(String.valueOf(c.getRGB()));
                label1.setForeground(c);
            }
        });

        label2 = new JLabel("speed: ");
        label2.setPreferredSize(new Dimension(70, 25));
        label2.setForeground(new Color(-3395476));
        cb = new JComboBox();
        cb.setForeground(new Color(-3395476));

        for (int i = 1; i < 6; i++) {
            cb.addItem(i);
        }

        JPanel p3 = new JPanel();
        p3.add(label2);
        p3.add(cb);
        p3.setLayout(new FlowLayout());
        p3.setSize(new Dimension(150, 35));
        p3.setVisible(true);
        p3.setLocation(175, 215);


        btn2 = new JButton("Start countdown");
        btn2.setPreferredSize(new Dimension(135, 25));
        btn2.setForeground(new Color(-3395476));
        btn3 = new JButton("Stop");
        btn3.setPreferredSize(new Dimension(135, 25));
        btn3.setForeground(new Color(-3395476));
        JPanel p4 = new JPanel();
        p4.add(btn2);
        p4.add(btn3);
        p4.setLayout(new FlowLayout());
        p4.setSize(new Dimension(300, 35));
        p4.setVisible(true);
        p4.setLocation(100, 280);

        elapsed = 0;
        i = 0;


        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsed++;

                Integer speed = Integer.parseInt(cb.getSelectedItem().toString());

                if (rb1.isSelected()) {

                    if (elapsed >= d.getSeconds()) {
                        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
                        frame.setVisible(true);

                        if (i == 0) {
                            i = elapsed;
                        }
                        if (elapsed - i == 0) {
                            frame.getContentPane().setBackground(c);
                            i = i + speed + 1;
                        }
                    }
                    System.out.println("Timer: " + elapsed + " | Secunde ramase pana la declansarea ferestrei:  " + d.minusSeconds(elapsed).getSeconds());
                } else if (rb2.isSelected()) {

                    Long contor = Long.parseLong(tf2.getText());
                    if (i == 0 && contor == 0) {
                        i = contor + 1;
                    }

                    if (i == 0) {
                        i = contor;
                    }

                    if (elapsed >= contor) {
                        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
                        frame.setVisible(true);

                        if (elapsed - i == 0) {
                            frame.getContentPane().setBackground(c);
                            i = i + speed + 1;
                        }

                    }
                    System.out.print("Timer: " + elapsed + " | Secunde ramase pana la declansarea ferestrei ");
                    System.out.println(contor - elapsed);
                }
            }
        });
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (tf1.getValue() == null  && tf2.getText().isBlank() ) {
                    JOptionPane.showMessageDialog(null, "Nu ati introdus timpul de pornire");
                }
                if (label1.getText() == "No color selected") {
                    JOptionPane.showMessageDialog(null, "Alegeti o culoare");
                }

                if ((tf1.getValue() != null) && label1.getText() != "No color selected") {
                    rb1.setEnabled(false);
                    rb2.setEnabled(false);
                    tf1.setEnabled(false);
                    tf2.setEnabled(false);
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    cb.setEnabled(false);
                    time = LocalTime.now();
                    time1 = LocalTime.parse(tf1.getText());

                    if (Duration.between(time, time1).getSeconds() < 0) {
                        d = Duration.between(time, time1).plusSeconds(86400);
                    } else {
                        d = Duration.between(time, time1);
                    }
                    t.start();
                }

                if (!tf2.getText().isBlank() && label1.getText() != "No color selected") {
                    rb1.setEnabled(false);
                    rb2.setEnabled(false);
                    tf1.setEnabled(false);
                    tf2.setEnabled(false);
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    cb.setEnabled(false);
                    t.start();
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
                frame.dispose();
                btn3.setEnabled(false);
            }
        });

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(-21624));
        g.fillRect(0, 0, 500, 380);
    }
}
