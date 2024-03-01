package Models;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {
    private int id, room_id;
    private String season;
    public static final String GET_RESERVATIONS_QUERY = "SELECT * FROM reservation";

    public Reservation() {
    }

    public Reservation(int id, int room_id, String season) {
        this.id = id;
        this.room_id = room_id;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public static ArrayList<Reservation> getList(String query) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnector.getInstance().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Reservation reservation = new Reservation(resultSet.getInt("id"), resultSet.getInt("room_id"), resultSet.getString("season"));
                reservations.add(reservation);
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

        return reservations;
    }

    public static boolean add(int roomID, String season) {
        String query = "INSERT INTO reservation (room_id, season) VALUES (?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            if (Room.getByID(roomID) != null) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setInt(1, roomID);
                preparedStatement.setString(2, season);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("no-room");
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
        String deleteQuery = "DELETE FROM reservation WHERE id = ?";
        String validReservationQuery = "SELECT * FROM reservation WHERE id = ?";
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(validReservationQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            // checking whether reservation exists
            if (resultSet.next()) {
                preparedStatement = DBConnector.getInstance().prepareStatement(deleteQuery);
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-reservation");
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

    public static boolean update(int id, String season) {
        String query = "UPDATE reservation SET season = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            if (isSeasonValid(season)) {
                preparedStatement = DBConnector.getInstance().prepareStatement(query);
                preparedStatement.setString(1, season);
                preparedStatement.setInt(2, id);
                return preparedStatement.executeUpdate() != -1;
            } else {
                Helper.showMessage("invalid-season");
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

    private static boolean isSeasonValid(String pensionType) {
        return (pensionType.equalsIgnoreCase("summer") || pensionType.equalsIgnoreCase("spring") || pensionType.equalsIgnoreCase("winter") || pensionType.equalsIgnoreCase("fall"));
    }

    public static String searchQuery(String season) {
        String query = "SELECT * FROM reservation WHERE season LIKE '%{{season}}%'";
        query = query.replace("{{season}}", season);
        return query;
    }

    public static Reservation getByID(int id) {
        String query = "SELECT * FROM reservation WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reservation reservation = null;
        try {
            preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                reservation = new Reservation(resultSet.getInt("id"), resultSet.getInt("room_id"), resultSet.getString("season"));
            }
            return reservation;
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
        return reservation;
    }
}
