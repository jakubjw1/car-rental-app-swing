public class Car {

    private final String marka, model;
    private final int ID_samochodu, ID_klienta;

    public Car(int ID_samochodu, String marka, String model, int ID_klienta){
        this.ID_samochodu = ID_samochodu;
        this.marka = marka;
        this.model = model;
        this.ID_klienta = ID_klienta;
    }

    public int getID_samochodu() {
        return ID_samochodu;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getID_klienta() {
        return ID_klienta;
    }
}
