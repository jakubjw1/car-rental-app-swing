public class NewClient implements Client{
    private final String imie, nazwisko, email, nrTel, adres;

    public NewClient(String imie, String nazwisko, String email, String nrTel, String adres){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.nrTel = nrTel;
        this.adres = adres;
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

    public String toString(){
        return "NewClient{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", nrTel=" + nrTel +
                ", adres=" + adres +
                '}';
    }
}
