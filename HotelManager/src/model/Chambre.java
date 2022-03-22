package model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Chambre {
    private int numero;
    private int etage;
    private double prix;

    private ArrayList<Reservation> reservations;
    private ArrayList<Sejour> sejours;

    public Chambre(int num, int e, double p) {
        numero = num;
        etage = e;
        prix = p;
        reservations = new ArrayList<>();
        sejours = new ArrayList<>();
    }

    public boolean disponible(Date debut, Date fin) {
        if (debut == null || fin == null)
            throw new IllegalArgumentException("Les arguments de model.Chambre.disponible ne peuvent pas être nuls");

        for (Reservation r : reservations) {
            if ((fin.after(r.getDebut()) && fin.before(r.getFin())) || (debut.after(r.getDebut()) && debut.before(r.getFin())))
                return false;
        }

        return true;
    }

    public int getNumero() {
        return numero;
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
}
