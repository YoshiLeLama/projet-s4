package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Chambre {
    private final Hotel hotel;

    private final int numero;
    private final int etage;
    private double prix;

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    private final ArrayList<Reservation> reservations;
    private final ArrayList<Sejour> sejours;

    public Chambre(Hotel hotel, int numero, int etage, double prix) {
        this.hotel = hotel;
        this.numero = numero;
        this.etage = etage;
        this.prix = prix;
        reservations = new ArrayList<>();
        sejours = new ArrayList<>();
    }

    public boolean disponible(Date debut, Date fin) {
        if (debut == null || fin == null)
            throw new IllegalArgumentException("Les arguments de model.Chambre.disponible ne peuvent pas être nuls");

        for (Reservation r : reservations) {
            if ((debut.equals(r.getFin()) || debut.before(r.getFin())) && (fin.equals(r.getDebut()) || fin.after(r.getDebut())))
                return false;
        }

        return true;
    }

    public int getNumero() {
        return numero;
    }

    public int getEtage() {
        return etage;
    }

    public void setPrix(double p) {
        prix = p;
    }

    public void ajouterReservation(Reservation reservation) {
        if (reservation == null) return;
        reservations.add(reservation);
    }

    public void ajouterSejour(Sejour sejour) {
        if (sejour == null) return;
        sejours.add(sejour);
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return "no. " + numero + " | étage no. " + etage + " | prix " + prix + "€";
    }
}
