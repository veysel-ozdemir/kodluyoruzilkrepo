package Views;

import Helper.Config;
import Helper.Helper;
import Models.Otel;
import Models.Reservation;
import Models.Room;
import Models.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private User admin;
    private JPanel wrapper;
    private JTabbedPane tabbedPane;
    private JTable userTable;
    private JLabel welcomeLabel;
    private JTextField searchUserFullnameTextField;
    private JComboBox searchUserTypeComboBox;
    private JButton searchUserButton;
    private JTable otelTable;
    private JTextField searchOtelNameTextField;
    private JTextField searchOtelAddressTextField;
    private JButton searchOtelButton;
    private JTable roomTable;
    private JTextField searchRoomNumberTextField;
    private JTextField searchRoomOtelIDTextField;
    private JButton searchRoomButton;
    private JComboBox searchRoomPensionTypeComboBox;
    private JTable reservationTable;
    private JComboBox searchReservationSeasonComboBox;
    private JButton searchReservationButton;
    private JTextField addUserFullnameTextField;
    private JTextField addUserUsernameTextField;
    private JTextField addUserPasswordTextField;
    private JButton addUserButton;
    private JComboBox addUserTypeComboBox;
    private JTextField deleteUserUserIDTextField;
    private JButton deleteUserButton;
    private JTextField addOtelNameTextField;
    private JTextField addOtelAddressTextField;
    private JButton deleteOtelButton;
    private JButton addOtelButton;
    private JTextField addOtelEmailTextField;
    private JTextField addOtelPhoneTextField;
    private JTextField addRoomNumberTextField;
    private JTextField deleteOtelOtelIDTextField;
    private JTextField addRoomOtelIDTextField;
    private JComboBox addRoomPensionTypeComboBox;
    private JButton addRoomButton;
    private JButton deleteRoomButton;
    private JTextField deleteRoomRoomIDTextField;
    private JComboBox addReservationSeasonComboBox;
    private JTextField addReservationRoomIDTextField;
    private JButton addReservationButton;
    private JTextField deleteReservationReservationIDTextField;
    private JButton deleteReservationButton;
    private JButton logOutButton;
    Object[] userRow, otelRow, roomRow, reservationRow;
    private DefaultTableModel userTableModel, otelTableModel, roomTableModel, reservationTableModel;

    public AdminGUI(User admin) {
        this.admin = admin;
        add(wrapper);
        setSize(1000, 800);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        welcomeLabel.setText("Welcome: " + admin.getFullname());
        logOutButton.addActionListener(e -> {
          LoginGUI loginGUI = new LoginGUI();
          dispose();
        });

        userSetup();
        otelSetup();
        roomSetup();
        reservationSetup();
    }

    private void reservationSetup() {
        reservationTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 2) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] identifiers = {"ID", "Season", "Room ID"};
        reservationTableModel.setColumnIdentifiers(identifiers);
        reservationRow = new Object[identifiers.length];
        loadReservationTable();
        reservationTable.setModel(reservationTableModel);
        reservationTable.getTableHeader().setReorderingAllowed(false);
        reservationTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedReservationID = reservationTable.getValueAt(reservationTable.getSelectedRow(), 0).toString();
                deleteReservationReservationIDTextField.setText(selectedReservationID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        reservationTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(reservationTable.getValueAt(reservationTable.getSelectedRow(), 0).toString());
                String season = reservationTable.getValueAt(reservationTable.getSelectedRow(), 1).toString();
                if (Reservation.update(id, season)) {
                    Helper.showMessage("done");
                }
                loadReservationTable();
            }
        });
        addReservationButton.addActionListener(e -> {
            if (Helper.isTextFieldEmpty(addReservationRoomIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    int roomID = Integer.parseInt(addReservationRoomIDTextField.getText().trim());
                    if (roomID <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else {
                        String season = addReservationSeasonComboBox.getSelectedItem().toString();
                        if (Reservation.add(roomID, season)) {
                            Helper.showMessage("done");
                        }
                        loadReservationTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        deleteReservationButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deleteReservationReservationIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deleteReservationReservationIDTextField.getText().trim());
                    if (id <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else if (Reservation.delete(id)) {
                        Helper.showMessage("done");
                        loadReservationTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        searchReservationButton.addActionListener(e -> {
            String season = searchReservationSeasonComboBox.getSelectedItem().toString();
            loadReservationTable(Reservation.getList(Reservation.searchQuery(season)));
        });
    }

    private void loadReservationTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) reservationTable.getModel();
        clearTableModel.setRowCount(0);

        for (Reservation reservation : Reservation.getList(Reservation.GET_RESERVATIONS_QUERY)) {
            reservationRow[0] = reservation.getId();
            reservationRow[1] = reservation.getSeason();
            reservationRow[2] = reservation.getRoom_id();
            reservationTableModel.addRow(reservationRow);
        }
    }

    private void loadReservationTable(ArrayList<Reservation> reservations) {
        DefaultTableModel clearTableModel = (DefaultTableModel) reservationTable.getModel();
        clearTableModel.setRowCount(0);

        for (Reservation reservation : reservations) {
            reservationRow[0] = reservation.getId();
            reservationRow[1] = reservation.getSeason();
            reservationRow[2] = reservation.getRoom_id();
            reservationTableModel.addRow(reservationRow);
        }
    }

    private void roomSetup() {
        roomTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] identifiers = {"ID", "Number", "Otel ID", "Pension Type"};
        roomTableModel.setColumnIdentifiers(identifiers);
        roomRow = new Object[identifiers.length];
        loadRoomTable();
        roomTable.setModel(roomTableModel);
        roomTable.getTableHeader().setReorderingAllowed(false);
        roomTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedRoomID = roomTable.getValueAt(roomTable.getSelectedRow(), 0).toString();
                deleteRoomRoomIDTextField.setText(selectedRoomID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        roomTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(roomTable.getValueAt(roomTable.getSelectedRow(), 0).toString());
                String type = roomTable.getValueAt(roomTable.getSelectedRow(), 3).toString();
                if (Room.update(id, type)) {
                    Helper.showMessage("done");
                }
                loadRoomTable();
            }
        });
        addRoomButton.addActionListener(e -> {
            if (Helper.isTextFieldEmpty(addRoomNumberTextField) || Helper.isTextFieldEmpty(addRoomOtelIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    int number = Integer.parseInt(addRoomNumberTextField.getText().trim());
                    int otelID = Integer.parseInt(addRoomOtelIDTextField.getText().trim());
                    if (number <= 0 || otelID <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else {
                        String type = addRoomPensionTypeComboBox.getSelectedItem().toString();
                        if (Room.add(number, otelID, type)) {
                            Helper.showMessage("done");
                        }
                        loadRoomTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-number-otel");
                }
            }
        });
        deleteRoomButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deleteRoomRoomIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deleteRoomRoomIDTextField.getText().trim());
                    if (id <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else if (Room.delete(id)) {
                        Helper.showMessage("done");
                        loadRoomTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        searchRoomButton.addActionListener(e -> {
            int number, otelID;
            String type;
            try {
                if (Helper.isTextFieldEmpty(searchRoomNumberTextField) && Helper.isTextFieldEmpty(searchRoomOtelIDTextField)) {
                    type = searchRoomPensionTypeComboBox.getSelectedItem().toString();
                    loadRoomTable(Room.getList(Room.searchByTypeQuery(type)));
                } else if ((!Helper.isTextFieldEmpty(searchRoomNumberTextField) && Helper.isTextFieldEmpty(searchRoomOtelIDTextField))|| (Helper.isTextFieldEmpty(searchRoomNumberTextField) && !Helper.isTextFieldEmpty(searchRoomOtelIDTextField))) {
                    Helper.showMessage("fill");
                } else {
                    number = Integer.parseInt(searchRoomNumberTextField.getText().trim());
                    otelID = Integer.parseInt(searchRoomOtelIDTextField.getText().trim());
                    type = searchRoomPensionTypeComboBox.getSelectedItem().toString();
                    loadRoomTable(Room.getList(Room.searchFullyQuery(number, otelID, type)));
                }
            } catch (NumberFormatException err) {
                Helper.showMessage("invalid-number-otel");
            }
        });
    }

    private void loadRoomTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) roomTable.getModel();
        clearTableModel.setRowCount(0);

        for (Room room : Room.getList(Room.GET_ROOMS_QUERY)) {
            roomRow[0] = room.getId();
            roomRow[1] = room.getNumber();
            roomRow[2] = room.getOtelID();
            roomRow[3] = room.getPensionType();
            roomTableModel.addRow(roomRow);
        }
    }

    private void loadRoomTable(ArrayList<Room> rooms) {
        DefaultTableModel clearTableModel = (DefaultTableModel) roomTable.getModel();
        clearTableModel.setRowCount(0);

        for (Room room : rooms) {
            roomRow[0] = room.getId();
            roomRow[1] = room.getNumber();
            roomRow[2] = room.getOtelID();
            roomRow[3] = room.getPensionType();
            roomTableModel.addRow(roomRow);
        }
    }

    private void otelSetup() {
        otelTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] identifiers = {"ID", "Name", "Address", "Email", "Phone"};
        otelTableModel.setColumnIdentifiers(identifiers);
        otelRow = new Object[identifiers.length];
        loadOtelTable();
        otelTable.setModel(otelTableModel);
        otelTable.getTableHeader().setReorderingAllowed(false);
        otelTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedOtelID = otelTable.getValueAt(otelTable.getSelectedRow(), 0).toString();
                deleteOtelOtelIDTextField.setText(selectedOtelID);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        otelTable.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(otelTable.getValueAt(otelTable.getSelectedRow(), 0).toString());
                String name = otelTable.getValueAt(otelTable.getSelectedRow(), 1).toString();
                String address = otelTable.getValueAt(otelTable.getSelectedRow(), 2).toString();
                String email = otelTable.getValueAt(otelTable.getSelectedRow(), 3).toString();
                String phone = otelTable.getValueAt(otelTable.getSelectedRow(), 4).toString();
                if (Otel.update(id, name, address, email, phone)) {
                    Helper.showMessage("done");
                }
                loadOtelTable();
            }
        });
        addOtelButton.addActionListener(e -> {
            if (Helper.isTextFieldEmpty(addOtelNameTextField) || Helper.isTextFieldEmpty(addOtelAddressTextField) || Helper.isTextFieldEmpty(addOtelEmailTextField) || Helper.isTextFieldEmpty(addOtelPhoneTextField)) {
                Helper.showMessage("fill");
            } else {
                String name = addOtelNameTextField.getText().trim();
                String address = addOtelAddressTextField.getText().trim();
                String email = addOtelEmailTextField.getText().trim();
                String phone = addOtelPhoneTextField.getText().trim();
                if (Otel.add(name, address, email, phone)) {
                    Helper.showMessage("done");
                }
                loadOtelTable();
            }
        });
        deleteOtelButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deleteOtelOtelIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deleteOtelOtelIDTextField.getText().trim());
                    if (id <= 0) {
                        throw new NumberFormatException("Invalid value entered (negative or zero)");
                    } else if (Otel.delete(id)) {
                        Helper.showMessage("done");
                        loadOtelTable();
                    }
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    Helper.showMessage("invalid-id");
                }
            }
        });
        searchOtelButton.addActionListener(e -> {
            String name = searchOtelNameTextField.getText().trim();
            String address = searchOtelAddressTextField.getText().trim();
            loadOtelTable(Otel.getList(Otel.searchQuery(name, address)));
        });
    }

    private void loadOtelTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) otelTable.getModel();
        clearTableModel.setRowCount(0);

        for (Otel otel : Otel.getList(Otel.GET_OTELS_QUERY)) {
            otelRow[0] = otel.getId();
            otelRow[1] = otel.getName();
            otelRow[2] = otel.getAddress();
            otelRow[3] = otel.getEmail();
            otelRow[4] = otel.getPhone();
            otelTableModel.addRow(otelRow);
        }
    }

    private void loadOtelTable(ArrayList<Otel> otels) {
        DefaultTableModel clearTableModel = (DefaultTableModel) otelTable.getModel();
        clearTableModel.setRowCount(0);

        for (Otel otel : otels) {
            otelRow[0] = otel.getId();
            otelRow[1] = otel.getName();
            otelRow[2] = otel.getAddress();
            otelRow[3] = otel.getEmail();
            otelRow[4] = otel.getPhone();
            otelTableModel.addRow(otelRow);
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
        Object[] identifiers = {"ID", "Full Name", "Username", "Password", "User Type"};
        userTableModel.setColumnIdentifiers(identifiers);
        userRow = new Object[identifiers.length];
        loadUserTable();
        userTable.setModel(userTableModel);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectedUserID = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
                deleteUserUserIDTextField.setText(selectedUserID);
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
                String type = addUserTypeComboBox.getSelectedItem().toString();
                if (User.add(name, username, password, type)) {
                    Helper.showMessage("done");
                }
                loadUserTable();
            }
        });
        deleteUserButton.addActionListener(e -> {
            int id;
            if (Helper.isTextFieldEmpty(deleteUserUserIDTextField)) {
                Helper.showMessage("fill");
            } else {
                try {
                    id = Integer.parseInt(deleteUserUserIDTextField.getText().trim());
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
            String name = searchUserFullnameTextField.getText().trim();
            String type = searchUserTypeComboBox.getSelectedItem().toString();
            loadUserTable(User.getList(User.searchQuery(name, type)));
        });
    }

    private void loadUserTable() {
        DefaultTableModel clearTableModel = (DefaultTableModel) userTable.getModel();
        clearTableModel.setRowCount(0);

        for (User user : User.getList(User.GET_USERS_QUERY)) {
            userRow[0] = user.getId();
            userRow[1] = user.getFullname();
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
            userRow[1] = user.getFullname();
            userRow[2] = user.getUsername();
            userRow[3] = user.getPassword();
            userRow[4] = user.getType();
            userTableModel.addRow(userRow);
        }
    }
}
