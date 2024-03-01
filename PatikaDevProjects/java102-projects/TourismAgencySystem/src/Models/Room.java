package Models;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id, number, otelID;
    private String pensionType;
    public static final String GET_ROOMS_QUERY = "SELECT * FROM room";

    public Room() {
    }

    public Room(int id, int number, int otelID, String pensionType) {
        this.id = id;
        this.number = number;
        this.otelID = otelID;
        this.pensionType = pensionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOtelID() {
        return otelID;
    }

    public void setOtelID(int otelID) {
        this.otelID = otelID;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public static ArrayList<Room> getList(String query) {
        ArrayList<Room> rooms = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnector.getInstance().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Room room = new Room(resultSet.getInt("id"), resultSet.getInt("number"), resultSet.getInt("otel_id"), resultSet.getString("pension_type"));
                rooms.add(room);
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

        return rooms;
    }

    public static boolean add(int number, int otelID, String pensionType) {
        String query = "INSERT INTO room (number, otel_id, pension_type) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if (Otel.getByID(otelID) != null) {
                if (isRoomAvailable(number, otelID)) {
                    preparedStatement = DBConnector.getInstance().prepareStatement(query);
                    preparedStatement.setInt(1, number);
                    preparedStatement.setInt(2, otelID);
                    preparedStatement.setString(3, pensionType);
                    return preparedStatement.executeUpdate() != -1;
                } else {
                    Helper.showMessage("room-exists");
                    return false;
                }
            } else {
                Helper.showMessage("no-otel");
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

    public static boolean isRoomAvailable(int number, int otelID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM room WHERE number = ? AND otel_id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, otelID);
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
        String deleteQuery = "DELETE FROM room WHERE id = ?";
        String validRoomQuery = "SELECT * FROM room WHERE id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(validRoomQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            // checking whether room exists
            if (resultSet.next()) {
                preparedStatement = DBConnector.getInstance().prepareStatement(deleteQuery);
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-room");
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

    public static boolean update(int id, String pensionType) {
        String query = "UPDATE room SET pension_type = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            if (isPensionTypeValid(pensionType)) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, pensionType);
                preparedStatement.setInt(2, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-pension-type");
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

    private static boolean isPensionTypeValid(String pensionType) {
        return (pensionType.equalsIgnoreCase("ultra all inclusive") || pensionType.equalsIgnoreCase("all inclusive") || pensionType.equalsIgnoreCase("bed and breakfast") || pensionType.equalsIgnoreCase("full pension") || pensionType.equalsIgnoreCase("half pension"));
    }

    public static String searchFullyQuery(int number, int otelID, String type) {
        String query = "SELECT * FROM room WHERE number = {{number}} AND otel_id = {{otelID}} AND pension_type LIKE '%{{type}}%'";
        query = query.replace("{{number}}", String.valueOf(number));
        query = query.replace("{{otelID}}", String.valueOf(otelID));
        query = query.replace("{{type}}", type);
        return query;
    }
    public static String searchByTypeQuery(String type) {
        return "SELECT * FROM room WHERE pension_type LIKE '%{{type}}%'".replace("{{type}}", type);
    }

    public static Room getByID(int id) {
        String query = "SELECT * FROM room WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Room room = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room(resultSet.getInt("id"), resultSet.getInt("number"), resultSet.getInt("otel_id"), resultSet.getString("pension_type"));
            }
            return room;
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
        return room;
    }
}
