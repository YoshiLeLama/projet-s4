package view;

import model.Reservation;

import javax.swing.*;
import java.awt.*;

public class SejourView extends  JDialog {
    private final JTextField beginDateField;
    private final JTextField endDateField;
    private final JTextField consommationsField;

    private final JButton confirmButton;
    private final JButton cancelButton;

    private final Reservation reservation;

    public SejourView(JFrame parent, Reservation reservation) {
        super(parent, true);

        this.reservation = reservation;

        getContentPane().setLayout(new BorderLayout());

        beginDateField = new JTextField();
        endDateField = new JTextField();
        consommationsField = new JTextField();

        confirmButton = new JButton("Confirmer");
        cancelButton = new JButton("Annuler");

        JPanel bottomButtons = new JPanel(new FlowLayout());
        bottomButtons.add(confirmButton);
        bottomButtons.add(cancelButton);

        getContentPane().add(bottomButtons, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(300, 300));
        setVisible(true);
    }

    public boolean showSejour() {
        return true;
    }
}
