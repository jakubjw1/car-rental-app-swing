public class RegularClient implements Client {
    private final String imie, nazwisko, email, nrTel, adres;
    private final int nrKarty;


    public RegularClient(String imie, String nazwisko, String email, String nrTel, String adres, int nrKarty){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.nrTel = nrTel;
        this.adres = adres;
        this.nrKarty = nrKarty;
    }

    public String getImie(){
        return imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public String getEmail(){
        return email;
    }

    public String getNrTel(){
        return nrTel;
    }

    public String getAdres(){
        return adres;
    }

    public int getNrKarty(){
        return nrKarty;
    }

    public String toString(){
        return "RegularClient{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", nrTel=" + nrTel +
                ", adres=" + adres +
                ", nrKarty=" + nrKarty +
                '}';
    }
}
