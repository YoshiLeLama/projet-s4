package controller;

import model.Chambre;
import model.Client;
import model.Hotel;
import model.Reservation;
import view.ReservationListView;

import java.util.ArrayList;
import java.util.Date;

public class ReservationListController {
    private Hotel model;
    private ArrayList<Reservation> reservations;
    private ReservationListView view;

    public ReservationListController(Hotel model, ReservationListView view) {
        this.model = model;
        this.view = view;

        view.setupEvents(this);

        reservations = new ArrayList<>();

        view.modelUpdated(reservations);
    }

    public boolean addReservation(int clientId, Date debut, Date fin, int numeroChambre) {
        for (Client client : model.getClients()) {
            if (client.getId() == clientId) {
                for (Chambre chambre : model.getChambres()) {
                    if (chambre.getNumero() == numeroChambre) {
                        client.ajouterReservation(debut, fin, chambre);

                        reservations = model.getReservations();

                        view.modelUpdated(reservations);

                        model.onReservationUpdate();

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void deleteReservation(int reservationIndex) {
        int searchId = reservations.get(reservationIndex).getId();

        for (Client client : model.getClients()) {
            if (client.getReservations().removeIf(reservation -> reservation.getId() == searchId))
                break;
        }

        for (Chambre chambre : model.getChambres()) {
            if (chambre.getReservations().removeIf(reservation -> reservation.getId() == searchId))
                break;
        }

        reservations = model.getReservations();

        view.modelUpdated(reservations);

        model.onReservationUpdate();
    }
}
