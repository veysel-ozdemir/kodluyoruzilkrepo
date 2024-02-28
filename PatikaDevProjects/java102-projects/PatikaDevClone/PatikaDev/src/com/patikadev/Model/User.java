package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class User {
    private int id;
    private String name, username, password, type;
    public static final String GET_USERS_QUERY = "SELECT * FROM user";

    public User() {
    }

    public User(int id, String name, String username, String password, String type) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                User user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"));
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

    public static boolean add(String name, String username, String password, String type) {
        String query = "INSERT INTO user (name, username, password, type) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if (isUsernameAvailable(username, -1)) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, name);
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

    public static boolean update(int id, String name, String username, String password, String type) {
        String query = "UPDATE user SET name = ?, username = ?, password = ?, type = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            if (isTypeValid(type)) {
                if (isUsernameAvailable(username, id)) {
                    preparedStatement = DBConnector.getInstance().prepareStatement(query);
                    preparedStatement.setString(1, name);
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
                Helper.showMessage("invalid-type");
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
        return (type.equalsIgnoreCase("student") || type.equalsIgnoreCase("educator") || type.equalsIgnoreCase("operator"));
    }

    public static boolean isEducatorValid(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM user WHERE id = ? AND type = 'educator'";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public static String searchQuery(String name, String username, String type) {
        String query = "SELECT * FROM user WHERE username LIKE '%{{username}}%' AND name LIKE '%{{name}}%' AND type LIKE '%{{type}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{username}}", username);
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
                user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"));
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

    public static User getOperatorByCredentials(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ? AND type = 'operator'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"));
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
