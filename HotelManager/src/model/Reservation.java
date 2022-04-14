package model;

import view.ReservationListView;

import java.util.Date;

public class Reservation {
    private static int idCnt;

    private final int id;
    private Date debut;
    private Date fin;

    private Client client;
    private Chambre chambre;

    private boolean honored;

    public Reservation(Date debut, Date fin, Client client, Chambre chambre) {
        if (debut == null || fin == null || client == null || chambre == null)
            throw new IllegalArgumentException("les arguments du constructeur de model.Reservation ne peuvent pas être nuls");

        id = idCnt++;
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.chambre = chambre;
        chambre.ajouterReservation(this);
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public int getId() {
        return id;
    }

    public boolean isHonored() {
        return honored;
    }

    public void setHonored(boolean honored) {this.honored = honored;}

    @Override
    public String toString() {
        return id + " | chambre : " + chambre.getNumero() +
                " | client : " + client.getId() +
                " | dates : " + ReservationListView.dateFormat.format(debut) +
                " -> " + ReservationListView.dateFormat.format(fin) +
                (honored ? " - TERMINÉE" : " - EN COURS");
    }
}
