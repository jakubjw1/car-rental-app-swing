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
                String nrTel = results.getString("NrTel");
                String adres = results.getString("Adres");
                int nrKarty = results.getInt("NrKarty");

                if (nrKarty != 0) {
                    RegularClient regularClient = new RegularClient(imie, nazwisko, email, nrTel, adres, nrKarty);
                    list.add(regularClient);
                } else {
                    NewClient newClient = new NewClient(imie, nazwisko, email, nrTel, adres);
                    list.add(newClient);
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Nie udalo sie polaczyc z baza klientow.");
            list.add(new NewClient("Jakub", "Wojtowicz", "jw122980@stud.ur.edu.pl", "695395695", "Stara Wieś, 544"));
            list.add(new NewClient("Marcin", "Kopytko", "mkopytko22@gmail.com", "245235232", "Malinówka, 655"));
            list.add(new RegularClient("Robert", "Konieczko", "robertkon12@gmail.com", "111222333", "Kobienice, 422", 1233));
            list.add(new RegularClient("Mateusz", "Szałajko", "matsza321@gmail.com", "900800898", "Brzozów, 42",1234));
            return list;
        }
    }

}