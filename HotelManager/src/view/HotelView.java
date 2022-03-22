package view;

import controller.HotelController;
import model.Hotel;

import javax.swing.*;
import java.awt.*;

public class HotelView extends JFrame {
    public ChambreView chambreView;
    public ClientView clientView;
    public ReservationView reservationView;

    public HotelView() {
        super("Hotel Manager");

        Hotel hotel = new Hotel();

        HotelController controller = new HotelController(hotel, this);

        add(new ChambreView());

        setMinimumSize(new Dimension(400, 400));
        pack();
        setVisible(true);
    }
}
