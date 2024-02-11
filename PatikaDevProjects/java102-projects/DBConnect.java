import java.sql.*;

public class DBConnect {
    static final String DB_URL = "jdbc:mysql://localhost/university";
    static final String DB_USER = "backend_user";
    static final String DB_PASSWORD = "backend_user";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String retrieveQuery = "SELECT * FROM student";
        String insertQuery = "INSERT INTO student (student_name, student_mail) VALUES ('Paulina Paul', 'paulina@paul.com')";
        String preparedInsertQuery = "INSERT INTO student (student_name, student_mail) VALUES (?, ?)";
        String updateQuery = "UPDATE student SET student_name = 'Cris Paul', student_mail = 'cris@paul.com' WHERE student_id = 5";
        String preparedUpdateQuery = "UPDATE student SET student_name = ?, student_mail = ? WHERE student_id = ?";
        String deleteQuery = "DELETE FROM student WHERE student_id = 6";
        String preparedDeleteQuery = "DELETE FROM student WHERE student_id = ?";

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();

            // Retrieve data
            /*resultSet = st.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("student_id")); // resultSet.getInt(1);
                System.out.println("Name: " + resultSet.getString("student_name")); // resultSet.getString(2);
                System.out.println("Mail: " + resultSet.getString("student_mail")); // resultSet.getString(3);
                System.out.println("-----------------------");
            }*/

            // Insert data
            /*statement.executeUpdate(insertQuery);
            // Insert data with PreparedStatement
            preparedStatement = connection.prepareStatement(preparedInsertQuery);
            preparedStatement.setString(1, "Jack Jackson");
            preparedStatement.setString(2, "jack@jackson.com");
            preparedStatement.executeUpdate();*/

            // Update data
            /*statement.executeUpdate(updateQuery);
            // Update data with PreparedStatement
            preparedStatement = connection.prepareStatement(preparedUpdateQuery);
            preparedStatement.setString(1, "Paulina Paul");
            preparedStatement.setString(2, "paulina@paul.com");
            preparedStatement.setInt(3, 5);
            preparedStatement.executeUpdate();*/

            // Delete data
            /*statement.executeUpdate(deleteQuery);
            // Delete data with PreparedStatement
            preparedStatement = connection.prepareStatement(preparedDeleteQuery);
            preparedStatement.setInt(1, 5);
            preparedStatement.executeUpdate();*/

            // Transaction Management
            connection.setAutoCommit(false); // disabling auto committing
            // 1. Transaction
            preparedStatement = connection.prepareStatement("INSERT INTO student (student_name, student_mail) VALUES (?, ?)");
            preparedStatement.setString(1, "Franz Franklin");
            preparedStatement.setString(2, "franz@franklin.com");
            preparedStatement.executeUpdate();

            // Throw an exception
            if (true) {
                throw new SQLException("-- An exception occurred --");
            }

            // 2. Transaction
            preparedStatement.setString(1, "Mark Marko");
            preparedStatement.setString(2, "mark@marko.com");
            preparedStatement.executeUpdate();

            connection.commit(); // implement the transactions
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
                preparedStatement.close();
            } catch (SQLException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
