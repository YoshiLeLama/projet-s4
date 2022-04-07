package model;

public class SuitePresidentielle extends Chambre{
    public SuitePresidentielle(Hotel hotel, int numero, int etage, double prix) {
        super(hotel, numero, etage, prix);
    }

    @Override
    public String toString() {
        return "Suite présidentielle | " + super.toString();
    }
}
