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

    public void addReservation(int clientId, Date debut, Date fin, int numeroChambre) throws Exception {
        for (Client client : model.getClients()) {
            if (client.getId() == clientId) {
                for (Chambre chambre : model.getChambres()) {
                    if (chambre.getNumero() == numeroChambre) {
                        if (!chambre.disponible(debut, fin)) {
                            throw new Exception("La chambre n'est pas disponible");
                        }

                        client.ajouterReservation(debut, fin, chambre);

                        reservations = model.getReservations();

                        view.modelUpdated(reservations);

                        model.onReservationUpdate();

                        return;
                    }
                }
                throw new Exception("Le numéro de chambre n'existe pas");
            }
        }
        throw new Exception("Le numéro de client n'existe pas");
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
