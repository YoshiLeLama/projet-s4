import controller.ChambreListController;
import model.Chambre;
import model.ChambreDouble;
import model.ChambreSimple;
import model.Hotel;
import view.ChambreListView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotelManager {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.ajouterChambre(new ChambreSimple(hotel, 1, 1, 60));
        hotel.ajouterChambre(new ChambreDouble(hotel, 2, 1, 60));
        ChambreListView view = new ChambreListView();

        ChambreListController controller = new ChambreListController(hotel.getChambres(), view);

        JFrame frame = new JFrame("Hotel");
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setContentPane(view);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
