package model;

public class ChambreSimple extends Chambre {

    public ChambreSimple(Hotel hotel, int numero, int etage, double prix) {
        super(hotel, numero, etage, prix);
    }

    @Override
    public String toString() {
        return "Chambre simple | " + super.toString();
    }
}
