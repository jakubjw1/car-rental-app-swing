import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String host, username, password;

    public Database(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public ArrayList<Client> getClients() {
        ArrayList<Client> list = new ArrayList<>();

        Connection connection;

        try {
            connection = DriverManager.getConnection(this.host, this.username, this.password);
            String query = "select * from stuff";
            Statement stat = connection.createStatement();
            ResultSet results = stat.executeQuery(query);

            while (results.next()) {
                String producer = results.getString("Producer");
                String name = results.getString("Name");
                String number = results.getString("Number");
                double price = results.getDouble("Price");
                double volume = results.getDouble("Volume");
                int remaining = results.getInt("Remaining");
                int type = results.getInt("Type_ID");

                if (type == 1) {
                    Food f = new Food(producer, name, number, price, remaining);
                    list.add(f);
                } else {
                    Drink d = new Drink(producer, name, number, price, volume, remaining);
                    list.add(d);
                }
            }

            return list;

        } catch (SQLException e) {
            System.out.println("Nie udalo sie polaczyc z baza produktow.");
            list.add(new Food("Lajkonik", "Paluszki", "11", 3.50,  2));
            list.add(new Food("Lajkonik", "Precelki", "12", 4.00,  3));
            list.add(new Drink("Sink", "Woda niegazowana", "21", 2.00, 0.5, 10));
            list.add(new Drink("Tymbark", "Sok jabłkowy", "22", 4.00, 0.5, 3));
            return list;
        }
    }
}