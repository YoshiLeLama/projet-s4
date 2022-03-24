import controller.ChambreListController;
import controller.ClientListController;
import model.*;
import view.ChambreListView;
import view.ClientListView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotelManager {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.ajouterChambre(new ChambreSimple(hotel, 1, 1, 60));
        hotel.ajouterChambre(new ChambreDouble(hotel, 2, 1, 60));

        hotel.ajouterClient(new Client("Antoine"));
        hotel.ajouterClient(new Client("Jean"));
        ChambreListView chambreView = new ChambreListView();

        ChambreListController chambreListController = new ChambreListController(hotel, chambreView);

        ClientListView clientView = new ClientListView();

        ClientListController clientListController = new ClientListController(hotel, clientView);

        JFrame frame = new JFrame("Hotel");
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setContentPane(clientView);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
