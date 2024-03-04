import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class MyTimer extends JFrame {
    private JPanel myPanel;

    public MyTimer(String title) {
        super(title);
        this.setContentPane(myPanel);
        this.setSize(new Dimension(500, 380));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
        this.setVisible(true);

    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

        Runnable myGUI = new Runnable() {
            @Override
            public void run() {
                int option = JOptionPane.showOptionDialog(null, "Choose option", "Option dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Settings", "Close"}, null);
                switch (option) {
//            case JOptionPane.CLOSED_OPTION:
//                JOptionPane.showMessageDialog(null, "Fereasta a fost inchisa");
                    case JOptionPane.YES_OPTION:
                        MyTimer f = new MyTimer("Settings");
                        MyComponent mc = null;
                        try {
                            mc = new MyComponent();
                        } catch (ParseException | BadLocationException e) {
                            e.printStackTrace();
                        }
                        f.add(mc);
                        f.setVisible(true);
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        break;
                }
            }
        };
        SwingUtilities.invokeAndWait(myGUI);
    }
}
