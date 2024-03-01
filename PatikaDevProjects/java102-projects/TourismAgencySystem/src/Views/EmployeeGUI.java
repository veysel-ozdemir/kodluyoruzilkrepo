package Views;

import Helper.Config;
import Helper.Helper;
import Models.User;
import javax.swing.*;

public class EmployeeGUI extends JFrame {
    private User employee;
    private JPanel wrapper;
    private JLabel welcomeLabel;
    private JButton logOutButton;

    public EmployeeGUI(User employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1000, 800);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        welcomeLabel.setText("Welcome: " + employee.getFullname());
        logOutButton.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });
    }
}
