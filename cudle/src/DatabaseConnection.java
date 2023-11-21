import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mahasiswa"; // Change this URL
        String user = "your_username"; // Change this
        String password = "your_password"; // Change this

        return DriverManager.getConnection(url, user, password);
    }

    public static void fetchDataByNIM(String nim, javax.swing.JTextField jTextField2, javax.swing.JTextField jTextField3, javax.swing.JTextField jTextField4) {
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM mahasiswa WHERE nim=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nim);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Found a matching record
                        String nama = resultSet.getString("nama");
                        String email = resultSet.getString("email");

                        // menemukan
                        jTextField2.setText(nama);
                        jTextField3.setText(email);
                        jTextField4.setText(""); // Clear any previous "Record not found" message
                    } else {
                        // tidak menemukan
                        jTextField2.setText("");
                        jTextField3.setText("");
                        jTextField4.setText("Record not found");
                    }
                }
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            e.printStackTrace();
        }
    }
}
