package Models;

import java.sql.*;
import java.util.ArrayList;
import Helper.DBConnector;
import Helper.Helper;

public class User {

    private int id;
    private String fullname, username, password, type;
    public static final String GET_USERS_QUERY = "SELECT * FROM user";

    public User() {
    }

    public User(int id, String fullname, String username, String password, String type) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList(String query) {
        ArrayList<User> users = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnector.getInstance().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"));
                users.add(user);
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

        return users;
    }

    public static boolean add(String fullname, String username, String password, String type) {
        String query = "INSERT INTO user (fullname, username, password, type) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if (isUsernameAvailable(username, -1)) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, fullname);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, type);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("user-exists");
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

    public static boolean isUsernameAvailable(String username, int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT username FROM user WHERE username = ? AND NOT id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, username);
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
        String deleteQuery = "DELETE FROM user WHERE id = ?";
        String validUserQuery = "SELECT * FROM user WHERE id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(validUserQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            // checking whether user exists
            if (resultSet.next()) {
                preparedStatement = DBConnector.getInstance().prepareStatement(deleteQuery);
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-user");
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

    public static boolean update(int id, String fullname, String username, String password, String type) {
        String query = "UPDATE user SET fullname = ?, username = ?, password = ?, type = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            if (isTypeValid(type)) {
                if (isUsernameAvailable(username, id)) {
                    preparedStatement = DBConnector.getInstance().prepareStatement(query);
                    preparedStatement.setString(1, fullname);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, type.toLowerCase());
                    preparedStatement.setInt(5, id);
                    return preparedStatement.executeUpdate() != -1;
                } else {
                    Helper.showMessage("user-exists");
                    return false;
                }
            } else {
                Helper.showMessage("invalid-user-type");
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

    private static boolean isTypeValid(String type) {
        return (type.equalsIgnoreCase("admin") || type.equalsIgnoreCase("employee"));
    }

    public static String searchQuery(String fullname,String type) {
        String query = "SELECT * FROM user WHERE fullname LIKE '%{{fullname}}%' AND type LIKE '%{{type}}%'";
        query = query.replace("{{fullname}}", fullname);
        query = query.replace("{{type}}", type);
        return query;
    }

    public static User getByID(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"));
            }
            return user;
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
        return user;
    }

    public static User getByCredentials(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("fullname"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"));
            }
            return user;
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
        return user;
    }
}