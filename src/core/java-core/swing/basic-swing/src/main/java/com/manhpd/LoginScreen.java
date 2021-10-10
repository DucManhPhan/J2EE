package com.manhpd;

import javax.swing.*;

public class LoginScreen {

    public static void main(String[] args) {
        // creating instance of JFrame
        JFrame frame = new JFrame("Login screen");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating panel
        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel);
        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel) {
        // we do not need to use layout
        panel.setLayout(null);

        // creating user label
        JLabel userLabel = new JLabel("User");

        // x, y, width, height
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // creating text field
        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // creating password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // creating login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

}
