package Views;

import Helper.Config;
import Helper.Helper;
import Models.User;

import javax.swing.*;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wrapperTop;
    private JPanel wrapperBottom;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPasswordField passwordField;

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
                User user = User.getByCredentials(usernameTextField.getText().trim(), passwordField.getText());
                if (user == null) {
                    Helper.showMessage("no-user");
                } else {
                    if (user.getType().equals("admin")) {
                        AdminGUI adminGUI = new AdminGUI(user);
                        dispose();
                    } else if (user.getType().equals("employee")){
                        EmployeeGUI employeeGUI = new EmployeeGUI(user);
                        dispose();
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
    }
}
