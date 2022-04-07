package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

public class Hotel {
    private ArrayList<Chambre> chambres;
    private ArrayList<Client> clients;

    private ArrayList<Consumer<Void>> onReservationUpdateCallbacks;

    public Hotel() {
        chambres = new ArrayList<>();
        clients = new ArrayList<>();
        onReservationUpdateCallbacks = new ArrayList<>();
    }

    public ArrayList<Chambre> getChambres() {
        return chambres;
    }

    public void ajouterChambre(Chambre chambre) {
        if (chambre != null)
            chambres.add(chambre);
    }

    public ArrayList<Chambre> chambresDisponibles(Date debut, Date fin) {
        ArrayList<Chambre> chambresDisponibles = new ArrayList<>();
        for (Chambre chambre : chambres) {
            if (chambre.disponible(debut, fin))
                chambresDisponibles.add(chambre);
        }

        return chambresDisponibles;
    }

    public void modifierPrixChambre(int id, double prix) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumero() == id) {
                chambre.setPrix(prix);
                break;
            }
        }
    }

    public void supprimerChambre(int numero) {
        for (int i = 0; i < chambres.size(); i++) {
            if (chambres.get(i).getNumero() == numero) {
                chambres.remove(i);
                break;
            }
        }
    }

    public void ajouterClient(Client client) {
        if (client != null)
            clients.add(client);
    }

    public void supprimerClient(int id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == id) {
                clients.remove(i);
                break;
            }
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Client client : clients) {
            reservations.addAll(client.getReservations());
        }

        return reservations;
    }

    public void addOnReservationUpdateCallback(Consumer<Void> function) {
        onReservationUpdateCallbacks.add(function);
    }

    public void onReservationUpdate() {
        for (Consumer<Void> function : onReservationUpdateCallbacks)
            function.accept(null);
    }
}
