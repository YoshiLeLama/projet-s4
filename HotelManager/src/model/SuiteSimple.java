package model;

public class SuiteSimple extends Chambre {
    public SuiteSimple(Hotel hotel, int numero, int etage, double prix) {
        super(hotel, numero, etage, prix);
    }

    @Override
    public String toString() {
        return "Suite simple | " + super.toString();
    }
}
