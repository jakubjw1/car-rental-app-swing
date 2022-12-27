import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static void main(String[] args) {
        // Pobierz dane z bazy danych
        String url = "jdbc:mysql://localhost:3306/test_db";
        String user = "root";
        String password = "password";
        String query = "SELECT * FROM employees";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            // Pobierz metadane zapytania
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Pobierz nazwy kolumn
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Pobierz dane z wyników zapytania
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            // Utwórz tabelę z danymi
            JTable table = new JTable(data, columnNames);

            // Wyświetl tabelę w oknie JFrame
            JFrame frame = new JFrame("Database Table Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Błąd podczas pobierania danych z bazy danych: " + e.getMessage());
        }
    }
}
