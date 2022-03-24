package model;

import java.util.Date;

public class Client {
    private static int idCnt;

    private int id;
    private String nom;

    private Reservation reservation;
    private Sejour sejour;

    public Client(String nom) {
        if (nom == null)
            throw new IllegalArgumentException("Le nom du client ne peut pas être nuls");
        id = idCnt++;
        this.nom = nom;
    }

    public void ajouterReservation(Date debut, Date fin, Chambre chambre) {
        reservation = new Reservation(debut, fin, this, chambre);
    }

    public void honorerSejour(Date debut, Date fin) {
        sejour = new Sejour(debut, fin, this, reservation.getChambre());
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " | nom : " + nom + " | reservation : " + (reservation == null ? "aucune" : reservation.getId());
    }

    public void setName(String newNom) {
        this.nom = newNom;
    }
}
