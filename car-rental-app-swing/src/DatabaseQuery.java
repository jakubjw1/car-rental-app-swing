import java.sql.*;

public class DatabaseQuery {

    private Connection con;

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
            return rs.getInt("id");
        } else {
            return -1;
        }
    }
    public void addRecord(String imie, String nazwisko, String email, String nrTel, String adres) throws SQLException{
        String query = "INSERT INTO klienci (Imie, Nazwisko, Email, NrTel, Adres, NrKarty) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3,email);
        st.setString(4,nrTel);
        st.setString(5,adres);
        st.setInt(6,0);
        st.executeUpdate();
    }

    public void editRecord(String imie, String nazwisko, String email, String nrTel, String adres, String NrKarty) throws SQLException{
        String query = "UPDATE klienci SET Imie = ?, Nazwisko = ?, Email = ?, NrTel = ?, Adres = ?, NrKarty = ? WHERE ID_klienta = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3,email);
        st.setString(4,nrTel);
        st.setString(5,adres);
        st.setString(6,NrKarty);
        st.setInt(7,getID(imie, nazwisko));
        st.executeUpdate();
    }


}
