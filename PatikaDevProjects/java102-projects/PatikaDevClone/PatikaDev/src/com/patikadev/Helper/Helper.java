package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static int screenCenter(String axis, Dimension dimension) {
        int result;
        switch (axis.toLowerCase()) {
            case "x":
                result = (Toolkit.getDefaultToolkit().getScreenSize().width - dimension.width) / 2;
                break;
            case "y":
                result = (Toolkit.getDefaultToolkit().getScreenSize().height - dimension.height) / 2;
                break;
            default:
                result = 0;
        }
        return result;
    }

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean isTextFieldEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }

    public static void showMessage(String str) {
        String message, title;
        switch (str.toLowerCase()) {
            case "fill":
                message = "Please fill the blanks.";
                title = "Error";
                break;
            case "done":
                message = "Operation finished successfully.";
                title = "Result";
                break;
            case "error":
                message = "Unexpected error occurred.";
                title = "Error";
                break;
            case "user-exists":
                message = "The username already exists. Try to enter another one.";
                title = "Caution";
                break;
            case "no-operator":
                message = "There is no operator with entered credentials.";
                title = "Caution";
                break;
            case "patika-exists":
                message = "The name already exists. Try to enter another one.";
                title = "Caution";
                break;
            case "invalid-id":
                message = "The ID must be positive integer.";
                title = "Error";
                break;
            case "invalid-user":
                message = "There is no user with corresponding ID.";
                title = "Error";
                break;
            case "invalid-educator":
                message = "There is no educator with corresponding ID.";
                title = "Error";
                break;
            case "invalid-patika":
                message = "There is no patika with corresponding ID.";
                title = "Error";
                break;
            case "invalid-type":
                message = "Invalid type entered. Enter only following three options:\n- student\n- educator\n- operator";
                title = "Error";
                break;
            default:
                message = str;
                title = "Info";
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
