package main;

import javax.swing.*;

public class User {
    private static String newPassword, newUser, newUrl;
    private static JTextField url, username, password;
    private static int option;
    static boolean connection = true;

    public static boolean connect() {

        url = new JTextField();
        username = new JTextField();
        password = new JPasswordField();

        Object[] message = {
                "URL: ", url,
                "Username: ", username,
                "Password: ", password
        };

        option = JOptionPane.showConfirmDialog(null, message, "Connect to db", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //Am lasat datele de conectare comentate pentru o testare mai usoara
//            url.setText("jdbc:mysql://localhost:3306/test");
//            password.setText("");
//            username.setText("root");
            newPassword = password.getText();
            newUser = username.getText();
            newUrl = url.getText();

            if (newPassword.isEmpty() || newUser.isEmpty() || newUrl.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all input fields to connect to db");
                connection = false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Connection canceled");
            connection = false;
        }
        return connection;
    }

    public static String getNewPassword() {
        return newPassword;
    }

    public static void setNewPassword(String newPassword) {
        User.newPassword = newPassword;
    }

    public static String getNewUser() {
        return newUser;
    }

    public static void setNewUser(String newUser) {
        User.newUser = newUser;
    }

    public static String getNewUrl() {
        return newUrl;
    }

    public static void setNewUrl(String newUrl) {
        User.newUrl = newUrl;
    }
}
