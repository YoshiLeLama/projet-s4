package model;

import java.util.ArrayList;
import java.util.Date;

public class Client {
    private static int idCnt;

    private int id;
    private String nom;

    private ArrayList<Reservation> reservations;
    private Sejour sejour;

    public Client(String nom) {
        if (nom == null)
            throw new IllegalArgumentException("Le nom du client ne peut pas être nuls");
        id = idCnt++;
        this.nom = nom;

        reservations = new ArrayList<>();
    }

    public void ajouterReservation(Date debut, Date fin, Chambre chambre) {
        reservations.add(new Reservation(debut, fin, this, chambre));
    }

    public void honorerSejour(Date debut, Date fin, Reservation reservation) {
        sejour = new Sejour(debut, fin, reservation.getChambre());
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        ArrayList<Reservation> currentReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (!reservation.isHonored())
                currentReservations.add(reservation);
        }

        StringBuilder value = new StringBuilder(id + " | nom : " + nom + " | reservations en cours : " + (currentReservations.isEmpty() ? "aucune" : ""));
        for (Reservation reservation : currentReservations) {
            value.append(" ").append(reservation.getId()).append(",");
        }

        return value.toString();
    }

    public void setName(String newNom) {
        this.nom = newNom;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}
