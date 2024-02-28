package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wrapperTop;
    private JPanel wrapperBottom;
    private JLabel logo;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginGUI() {
        add(wrapper);
        setSize(500, 500);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        loginButton.addActionListener(e -> {
           if (Helper.isTextFieldEmpty(usernameTextField) || Helper.isTextFieldEmpty(passwordField)) {
               Helper.showMessage("fill");
           } else {
               User user = User.getOperatorByCredentials(usernameTextField.getText().trim(), passwordField.getText());
               if (user == null) {
                   Helper.showMessage("no-operator");
               } else {
                   OperatorGUI operatorGUI = new OperatorGUI(user);
                   dispose();
               }
           }
        });
    }
}
