package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Course;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private final User operator;
    private JLabel welcomeLabel;
    private JTable userTable;
    private JTextField addUserFullnameTextField;
    private JTextField addUserUsernameTextField;
    private JComboBox addUserSubTypeComboBox;
    private JButton addUserButton;
    private JTabbedPane operatorTabbedPane;
    private JScrollPane usersScrollPane;
    private JPanel userFormPanel;
    private JPanel panelTop;
    private JButton logOutButton;
    private JTextField addUserPasswordTextField;
    private JButton deleteUserButton;
    private JTextField deleteUserIDTextField;
    private JTextField searchUserNameTextField;
    private JTextField searchUserUsernameTextField;
    private JComboBox searchUserSubTypeComboBox;
    private JButton searchUserButton;
    private JTable patikaTable;
    private JTextField searchPatikaNameTextField;
    private JButton searchPatikaButton;
    private JTextField addPatikaNameTextField;
    private JButton addPatikaButton;
    private JButton deletePatikaButton;
    private JPanel patikaFormPanel;
    private JTextField deletePatikaIDTextField;
    private JTable courseTable;
    private JTextField searchCourseNameTextField;
    private JButton searchCourseButton;
    private JTextField addCourseNameTextField;
    private JTextField addCourseLanguageTextField;
    private JButton addCourseButton;
    private JTextField deleteCourseIDTextField;
    private JButton deleteCourseButton;
    private JTextField searchCourseLanguageTextField;
    private JTextField addCourseEducatorIDTextField;
    private JTextField addCoursePatikaIDTextField;
    private DefaultTableModel userTableModel;
    private DefaultTableModel patikaTableModel;
    private DefaultTableModel courseTableModel;
    Object[] userRow;
    Object[] patikaRow;
    Object[] courseRow;
    public OperatorGUI(User user) {
        this.operator = user;
        add(wrapper);
        setSize(1000, 800);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        welcomeLabel.setText("Welcome: " + operator.getName());
        logOutButton.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });
        // Users Tab Panel
        userSetup();
        // Patikas Tab Panel
        patikaSetup();
        // Courses Tab Panel
        courseSetup();
    }

    private void courseSetup() {
        courseTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] identifiers = {"ID", "Educator Name", "Patika Name", "Course Name", "Course Language"};
        courseTableModel.setColumnIdentifiers(identifiers);
        courseRow = new Object[identifiers.length];
        loadCourseTable();
        courseTable.setModel(courseTableModel);
        courseTable.getTableHeader().setReorderingAllowed(false);
        courseTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedCourseID = courseTable.getValueAt(courseTable.getSelectedRow(), 0).toString();
                deleteCourseIDTextField.setText(selectedCourseID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        courseTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(courseTable.getValueAt(courseTable.getSelectedRow(), 0).toString());
                String name = courseTable.getValueAt(courseTable.getSelectedRow(), 3).toString();
                String language = courseTable.getValueAt(courseTable.getSelectedRow(), 4).toString();
                if (Course.update(id, name, language)) {
                    Helper.showMessage("done");
                }
                loadCourseTable();
            }
        });
        addCourseButton.addActionListener(e -> {
            if (Helper.isTextFieldEmpty(addCourseNameTextField) || Helper.isTextFieldEmpty(addCourseLanguageTextField) || Helper.isTextFieldEmpty(addCourseEducatorIDTextField) || Helper.isTextFieldEmpty(addCoursePatikaIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    String name = addCourseNameTextField.getText().trim();
                    String language = addCourseLanguageTextField.getText().trim();
                    int educatorID = Integer.parseInt(addCourseEducatorIDTextField.getText().trim());
                    int patikaID = Integer.parseInt(addCoursePatikaIDTextField.getText().trim());
                    if (educatorID <= 0 || patikaID <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    }
                    if (Course.add(educatorID, patikaID, name, language)) {
                        Helper.showMessage("done");
                    }
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
                loadCourseTable();
            }
        });
        deleteCourseButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deleteCourseIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deleteCourseIDTextField.getText().trim());
                    if (id <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else if (Course.delete(id)) {
                        Helper.showMessage("done");
                        loadCourseTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        searchCourseButton.addActionListener(e -> {
            String name = searchCourseNameTextField.getText().trim();
            String language = searchCourseLanguageTextField.getText().trim();
            loadCourseTable(Course.getList(Course.searchQuery(name, language)));
        });
    }

    private void loadCourseTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) courseTable.getModel();
        clearTableModel.setRowCount(0);

        for (Course course : Course.getList(Course.GET_COURSES_QUERY)) {
            course.setPatika(Patika.getByID(course.getPatikaID()));
            course.setEducator(User.getByID(course.getEducatorID()));
            courseRow[0] = course.getId();
            courseRow[1] = course.getEducator().getName();
            courseRow[2] = course.getPatika().getName();
            courseRow[3] = course.getName();
            courseRow[4] = course.getLanguage();
            courseTableModel.addRow(courseRow);
        }
    }

    private void loadCourseTable(ArrayList<Course> courses) {
        DefaultTableModel clearTableModel = (DefaultTableModel) courseTable.getModel();
        clearTableModel.setRowCount(0);

        for (Course course : courses) {
            course.setPatika(Patika.getByID(course.getPatikaID()));
            course.setEducator(User.getByID(course.getEducatorID()));
            courseRow[0] = course.getId();
            courseRow[1] = course.getEducator().getName();
            courseRow[2] = course.getPatika().getName();
            courseRow[3] = course.getName();
            courseRow[4] = course.getLanguage();
            courseTableModel.addRow(courseRow);
        }
    }

    private void patikaSetup() {
        patikaTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] identifiers = {"ID", "Name"};
        patikaTableModel.setColumnIdentifiers(identifiers);
        patikaRow = new Object[identifiers.length];
        loadPatikaTable();
        patikaTable.setModel(patikaTableModel);
        patikaTable.getTableHeader().setReorderingAllowed(false);
        patikaTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedPatikaID = patikaTable.getValueAt(patikaTable.getSelectedRow(), 0).toString();
                deletePatikaIDTextField.setText(selectedPatikaID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        patikaTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(patikaTable.getValueAt(patikaTable.getSelectedRow(), 0).toString());
                String name = patikaTable.getValueAt(patikaTable.getSelectedRow(), 1).toString();
                if (Patika.update(id, name)) {
                    Helper.showMessage("done");
                }
                loadPatikaTable();
            }
        });
        addPatikaButton.addActionListener(e -> {
            if (Helper.isTextFieldEmpty(addPatikaNameTextField)) {
                Helper.showMessage("fill");
            } else {
                String name = addPatikaNameTextField.getText().trim();
                if (Patika.add(name)) {
                    Helper.showMessage("done");
                }
                loadPatikaTable();
            }
        });
        deletePatikaButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deletePatikaIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deletePatikaIDTextField.getText().trim());
                    if (id <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else if (Patika.delete(id)) {
                        Helper.showMessage("done");
                        loadPatikaTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        searchPatikaButton.addActionListener(e -> {
            String name = searchPatikaNameTextField.getText().trim();
            loadPatikaTable(Patika.getList(Patika.searchQuery(name)));
        });
    }

    private void loadPatikaTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) patikaTable.getModel();
        clearTableModel.setRowCount(0);

        for (Patika patika : Patika.getList(Patika.GET_PATIKAS_QUERY)) {
            patikaRow[0] = patika.getId();
            patikaRow[1] = patika.getName();
            patikaTableModel.addRow(patikaRow);
        }
    }

    private void loadPatikaTable(ArrayList<Patika> patikas) {
        DefaultTableModel clearTableModel = (DefaultTableModel) patikaTable.getModel();
        clearTableModel.setRowCount(0);

        for (Patika patika : patikas) {
            patikaRow[0] = patika.getId();
            patikaRow[1] = patika.getName();
            patikaTableModel.addRow(patikaRow);
        }
    }

    private void userSetup() {
        userTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] identifiers = {"ID", "Full Name", "Username", "Password", "Subscription Type"};
        userTableModel.setColumnIdentifiers(identifiers);
        userRow = new Object[identifiers.length];
        loadUserTable();
        userTable.setModel(userTableModel);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedUserID = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
                deleteUserIDTextField.setText(selectedUserID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        userTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(userTable.getValueAt(userTable.getSelectedRow(), 0).toString());
                String name = userTable.getValueAt(userTable.getSelectedRow(), 1).toString();
                String username = userTable.getValueAt(userTable.getSelectedRow(), 2).toString();
                String password = userTable.getValueAt(userTable.getSelectedRow(), 3).toString();
                String type = userTable.getValueAt(userTable.getSelectedRow(), 4).toString();
                if (User.update(id, name, username, password, type)) {
                    Helper.showMessage("done");
                }
                loadUserTable();
            }
        });
        addUserButton.addActionListener(e -> {
            if (Helper.isTextFieldEmpty(addUserFullnameTextField) || Helper.isTextFieldEmpty(addUserUsernameTextField) || Helper.isTextFieldEmpty(addUserPasswordTextField)) {
                Helper.showMessage("fill");
            } else {
                String name = addUserFullnameTextField.getText().trim();
                String username = addUserUsernameTextField.getText().trim();
                String password = addUserPasswordTextField.getText().trim();
                String type = addUserSubTypeComboBox.getSelectedItem().toString();
                if (User.add(name, username, password, type)) {
                    Helper.showMessage("done");
                }
                loadUserTable();
            }
        });
        deleteUserButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deleteUserIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deleteUserIDTextField.getText().trim());
                    if (id <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else if (User.delete(id)) {
                        Helper.showMessage("done");
                        loadUserTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        searchUserButton.addActionListener(e -> {
            String name = searchUserNameTextField.getText().trim();
            String username = searchUserUsernameTextField.getText().trim();
            String type = searchUserSubTypeComboBox.getSelectedItem().toString();
            loadUserTable(User.getList(User.searchQuery(name, username, type)));
        });
    }

    private void loadUserTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) userTable.getModel();
        clearTableModel.setRowCount(0);

        for (User user : User.getList(User.GET_USERS_QUERY)) {
            userRow[0] = user.getId();
            userRow[1] = user.getName();
            userRow[2] = user.getUsername();
            userRow[3] = user.getPassword();
            userRow[4] = user.getType();
            userTableModel.addRow(userRow);
        }
    }

    private void loadUserTable(ArrayList<User> users) {
        DefaultTableModel clearTableModel = (DefaultTableModel) userTable.getModel();
        clearTableModel.setRowCount(0);

        for (User user : users) {
            userRow[0] = user.getId();
            userRow[1] = user.getName();
            userRow[2] = user.getUsername();
            userRow[3] = user.getPassword();
            userRow[4] = user.getType();
            userTableModel.addRow(userRow);
        }
    }
}
