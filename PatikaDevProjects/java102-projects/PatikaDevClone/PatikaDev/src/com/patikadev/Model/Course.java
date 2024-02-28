package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id, educatorID, patikaID;
    private String name, language;
    private Patika patika;
    private User educator;
    public static final String GET_COURSES_QUERY = "SELECT * FROM course";

    public Course() {
    }

    public Course(int id, int educatorID, int patikaID, String name, String language) {
        this.id = id;
        this.educatorID = educatorID;
        this.patikaID = patikaID;
        this.name = name;
        this.language = language;
        this.patika = Patika.getByID(id);
        this.educator = User.getByID(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEducatorID() {
        return educatorID;
    }

    public void setEducatorID(int educatorID) {
        this.educatorID = educatorID;
    }

    public int getPatikaID() {
        return patikaID;
    }

    public void setPatikaID(int patikaID) {
        this.patikaID = patikaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public static ArrayList<Course> getList(String query) {
        ArrayList<Course> courses = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnector.getInstance().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Course course = new Course(resultSet.getInt("id"), resultSet.getInt("educator_id"), resultSet.getInt("patika_id"), resultSet.getString("name"), resultSet.getString("language"));
                courses.add(course);
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

        return courses;
    }

    public static boolean add(int educatorID, int patikaID, String name, String language) {
        String query = "INSERT INTO course (educator_id, patika_id, name, language) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if (User.isEducatorValid(educatorID)) {
                if (Patika.isPatikaValid(patikaID)) {
                    preparedStatement = DBConnector.getInstance().prepareStatement(query);
                    preparedStatement.setInt(1, educatorID);
                    preparedStatement.setInt(2, patikaID);
                    preparedStatement.setString(3, name);
                    preparedStatement.setString(4, language);
                    return preparedStatement.executeUpdate() != -1;
                } else {
                    Helper.showMessage("invalid-patika");
                    return false;
                }
            } else {
                Helper.showMessage("invalid-educator");
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

    public static boolean delete(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String deleteQuery = "DELETE FROM course WHERE id = ?";
        String validUserQuery = "SELECT * FROM course WHERE id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(validUserQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            // checking whether course exists
            if (resultSet.next()) {
                preparedStatement = DBConnector.getInstance().prepareStatement(deleteQuery);
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-patika");
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

    public static boolean update(int id, String name, String language) {
        String query = "UPDATE course SET name = ?, language = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, language);
            preparedStatement.setInt(3, id);
            return preparedStatement.executeUpdate() != -1;
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

    public static String searchQuery(String name, String language) {
        String query = "SELECT * FROM course WHERE name LIKE '%{{name}}%' AND language LIKE '%{{language}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{language}}", language);
        return query;
    }

    public static Course getByID(int id) {
        String query = "SELECT * FROM course WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, id);
            if (resultSet.next()) {
                course = new Course(resultSet.getInt("id"), resultSet.getInt("educator_id"), resultSet.getInt("patika_id"), resultSet.getString("name"), resultSet.getString("language"));
            }
            return course;
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
        return course;
    }
}
