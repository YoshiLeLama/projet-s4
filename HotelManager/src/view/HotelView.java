package view;

import controller.HotelController;

import javax.swing.*;
import java.awt.*;

public class HotelView extends JFrame {
    public ChambreListView chambreView;
    public ClientListView clientView;
    public ReservationListView reservationView;

    public HotelView() {
        super("Hotel Manager");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new ChambreListView());

        setMinimumSize(new Dimension(400, 400));
        pack();
        setVisible(true);
    }

    public void setupEvents(HotelController controller) {

    }
}
