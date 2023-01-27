import java.sql.*;

public class DatabaseQuery {

    private final Connection con;

    public DatabaseQuery(String host, String username, String password) throws SQLException{
        this.con = DriverManager.getConnection(host, username, password);
    }

    public int getID(String imie, String nazwisko) throws SQLException {
        String query = "SELECT ID_klienta FROM klienci WHERE Imie = ? AND Nazwisko = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,imie);
        st.setString(2,nazwisko);
        ResultSet rs = st.executeQuery();
        if (rs.next()){
            return rs.getInt("ID_klienta");
        } else {
            return -1;
        }
    }
    public void addRecord(String imie, String nazwisko, String email, String nrTel, String adres) throws SQLException{
        String query = "INSERT INTO klienci (Imie, Nazwisko, Email, NrTel, Adres) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3,email);
        st.setString(4,nrTel);
        st.setString(5,adres);
        st.executeUpdate();
    }

    public void editRecord(String imie, String nazwisko, String email, String nrTel, String adres) throws SQLException{
        String query = "UPDATE klienci SET Imie = ?, Nazwisko = ?, Email = ?, NrTel = ?, Adres = ? WHERE ID_klienta = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3,email);
        st.setString(4,nrTel);
        st.setString(5,adres);
        st.setInt(6,getID(imie, nazwisko));
        st.executeUpdate();
    }

    public void deleteRecord(String imie, String nazwisko, String email, String nrTel, String adres) throws SQLException{
        String query = "DELETE FROM klienci WHERE Imie = ? AND Nazwisko = ? AND Email = ? AND NrTel = ? AND Adres = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3,email);
        st.setString(4,nrTel);
        st.setString(5,adres);
        st.executeUpdate();
    }

    public void addNrKarty(String imie, String nazwisko, int nrKarty) throws SQLException{
        String query = "UPDATE klienci SET NrKarty = ? WHERE ID_klienta = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,nrKarty);
        st.setInt(2,getID(imie,nazwisko));
        st.executeUpdate();
    }

    public void addCar(String marka, String model) throws SQLException{
        String query = "INSERT INTO samochody (marka, model) VALUES (?, ?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,marka);
        st.setString(2,model);
        st.executeUpdate();
    }

    public void deleteCar(String ID_samochodu) throws SQLException{
        String query = "DELETE FROM samochody WHERE ID_samochodu = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,ID_samochodu);
        st.executeUpdate();
    }

    public void orderCar(int ID_klienta, String ID_samochodu) throws SQLException{
        String query = "UPDATE samochody SET ID_klienta = ? WHERE ID_samochodu = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,ID_klienta);
        st.setString(2,ID_samochodu);
        st.executeUpdate();
    }

}
