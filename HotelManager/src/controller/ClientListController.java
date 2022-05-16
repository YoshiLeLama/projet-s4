package controller;

import model.Client;
import model.Hotel;
import view.ClientListView;

import java.util.function.Function;

public class ClientListController {
    private final Hotel model;
    private final ClientListView view;

    public ClientListController(Hotel model, ClientListView view) {
        this.model = model;
        this.view = view;

        model.addOnReservationUpdateCallback(unused -> view.modelUpdated(model.getClients()));

        view.setupEvents(this);
        view.modelUpdated(model.getClients());
    }

    public void addClient(String nom) {
        if (nom == null || nom.isEmpty())
            return;

        model.ajouterClient(new Client(nom));

        view.modelUpdated(model.getClients());
    }

    public void modifyClient(int index, String newNom) {
        model.getClients().get(index).setName(newNom);

        view.modelUpdated(model.getClients());
    }

    public void deleteClient(int selectedIndex) {
        Client client = model.getClients().get(selectedIndex);

        if (!client.getReservations().isEmpty()) {
            throw new RuntimeException("Il est impossible de supprimer un client qui a des réservations.");
        }

        model.getClients().remove(client);

        view.modelUpdated(model.getClients());
    }
}
