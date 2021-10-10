package com.manhpd;

import javax.swing.*;

public class HelloWorldSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static void createAndShowGUI() {
        // create and setup the window
        JFrame frame = new JFrame("Hello world Swing");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the ubiquitous "Hello world" label
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        // display the window
        frame.pack();
        frame.setVisible(true);
    }

}
