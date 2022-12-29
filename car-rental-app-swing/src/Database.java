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
            String query = "SELECT * FROM klienci";
            Statement stat = connection.createStatement();
            ResultSet results = stat.executeQuery(query);

            while (results.next()) {
                String imie = results.getString("Imie");
                String nazwisko = results.getString("Nazwisko");
                String email = results.getString("Email");
                String nrTel = results.getString("Numer Telefonu");
                String adres = results.getString("Adres");
                int nrKarty = results.getInt("Numer Karty");

                Client c = new Client(imie, nazwisko, email, nrTel, adres, nrKarty);
                list.add(c);
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