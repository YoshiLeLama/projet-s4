import controller.ChambreListController;
import controller.ClientListController;
import controller.ReservationListController;
import model.*;
import view.ChambreListView;
import view.ClientListView;
import view.ReservationListView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotelManager {

    public static void main(String[] args) {
        // Données de démonstration
        Hotel hotel = new Hotel();
        hotel.ajouterChambre(new ChambreSimple(hotel, 1, 1, 60));
        hotel.ajouterChambre(new ChambreDouble(hotel, 2, 1, 60));
        hotel.ajouterClient(new Client("Antoine"));
        hotel.ajouterClient(new Client("Jean"));
        ChambreListView chambreView = new ChambreListView();

        ChambreListController chambreListController = new ChambreListController(hotel, chambreView);

        ClientListView clientView = new ClientListView();

        ClientListController clientListController = new ClientListController(hotel, clientView);

        ReservationListView reservationListView = new ReservationListView();

        ReservationListController reservationListController = new ReservationListController(hotel, reservationListView);

        JFrame frame = new JFrame("Hotel");
        frame.setMinimumSize(new Dimension(1100, 500));

        JPanel contentPane = new JPanel(new GridLayout(2, 2, 2, 2));
        contentPane.add(chambreView);
        contentPane.add(clientView);
        contentPane.add(reservationListView);
        contentPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        frame.setContentPane(contentPane);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
