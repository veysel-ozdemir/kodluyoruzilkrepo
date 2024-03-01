package Models;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Otel {

    private int id;
    private String name, address, email, phone;
    public static final String GET_OTELS_QUERY = "SELECT * FROM otel";

    public Otel() {
    }

    public Otel(int id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static ArrayList<Otel> getList(String query) {
        ArrayList<Otel> otels = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnector.getInstance().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Otel otel = new Otel(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("email"), resultSet.getString("phone"));
                otels.add(otel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return otels;
    }

    public static boolean add(String name, String address, String email, String phone) {
        String query = "INSERT INTO otel (name, address, email, phone) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if (isNameAvailable(name, -1)) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, phone);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("otel-exists");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.showMessage("error");
            return false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNameAvailable(String name, int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT name FROM otel WHERE name = ? AND NOT id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean delete(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String deleteQuery = "DELETE FROM otel WHERE id = ?";
        String validOtelQuery = "SELECT * FROM otel WHERE id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(validOtelQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            // checking whether otel exists
            if (resultSet.next()) {
                preparedStatement = DBConnector.getInstance().prepareStatement(deleteQuery);
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-otel");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.showMessage("error");
            return false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean update(int id, String name, String address, String email, String phone) {
        String query = "UPDATE otel SET name = ?, address = ?, email = ?, phone = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            if (isNameAvailable(name, id)) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, phone);
                preparedStatement.setInt(5, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("otel-exists");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Helper.showMessage("error");
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String searchQuery(String name, String address) {
        String query = "SELECT * FROM otel WHERE address LIKE '%{{address}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{address}}", address);
        return query;
    }

    public static Otel getByID(int id) {
        String query = "SELECT * FROM otel WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Otel otel = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                otel = new Otel(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("email"), resultSet.getString("phone"));
            }
            return otel;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return otel;
    }
}
