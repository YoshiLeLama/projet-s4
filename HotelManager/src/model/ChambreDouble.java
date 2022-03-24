package model;

public class ChambreDouble extends Chambre {
    public ChambreDouble(Hotel hotel, int numero, int etage, double prix) {
        super(hotel, numero, etage, prix);
    }

    @Override
    public String toString() {
        return "Chambre double | " + super.toString();
    }
}
