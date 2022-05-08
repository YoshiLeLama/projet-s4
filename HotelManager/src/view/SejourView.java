package view;

import model.Reservation;
import model.Sejour;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SejourView extends JDialog {
    private final JTextField beginDateField;
    private final JTextField endDateField;
    private final JTextField consommationsField;

    private final JButton confirmButton;
    private final JButton cancelButton;

    private final Reservation reservation;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private boolean cancelled = false;

    public SejourView(JFrame parent, Reservation reservation) {
        super(parent, true);

        this.reservation = reservation;

        getContentPane().setLayout(new BorderLayout());

        beginDateField = new JTextField();
        endDateField = new JTextField();
        consommationsField = new JTextField("0.0");

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        northPanel.add(new JLabel("Date de début du séjour"));
        northPanel.add(beginDateField);
        northPanel.add(new JLabel("Date de fin du séjour"));
        northPanel.add(endDateField);
        northPanel.add(new JLabel("Prix total des consommations"));
        northPanel.add(consommationsField);

        getContentPane().add(northPanel, BorderLayout.NORTH);

        confirmButton = new JButton("Confirmer");
        cancelButton = new JButton("Annuler");

        JPanel bottomButtons = new JPanel(new FlowLayout());
        bottomButtons.add(confirmButton);
        bottomButtons.add(cancelButton);

        getContentPane().add(bottomButtons, BorderLayout.SOUTH);

        String debutResa, finResa;
        try {
            debutResa = dateFormat.format(reservation.getDebut());
            finResa = dateFormat.format(reservation.getFin());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "La date de début ou de fin de réservation est mal renseignée.",
                    "Erreur d'initialisation du séjour", JOptionPane.ERROR_MESSAGE);
            return;
        }

        beginDateField.setText(debutResa);
        endDateField.setText(finResa);

        pack();
        setMinimumSize(new Dimension(300, 300));

        confirmButton.addActionListener(e -> {
            cancelled = false;
            // On ferme le dialogue à la confirmation
            this.setVisible(false);
        });

        cancelButton.addActionListener(e -> {
            cancelled = true;
            this.setVisible(false);
        });
    }

    public Sejour showSejour(boolean b) {
        this.setVisible(b);

        if (cancelled)
            return null;

        if (beginDateField.getText().isEmpty() || beginDateField.getText().isEmpty() || consommationsField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Les champs du formulaire de séjour ne peuvent pas être vide.");
            return this.showSejour(true);
        }

        Date beginDate, endDate;
        try {
            beginDate = dateFormat.parse(beginDateField.getText());

            endDate = dateFormat.parse(endDateField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Les dates de séjour sont mal renseignées.");
            return this.showSejour(true);
        }

        if (beginDate.before(reservation.getDebut()) || beginDate.after(reservation.getFin())) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "La date de début de séjour ne peut pas être après la date de fin.");
            return this.showSejour(true);
        }

        if (endDate.after(reservation.getFin()) || endDate.before(beginDate)) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "La date de fin de séjour ne peut pas être avant la date de début.");
            return this.showSejour(true);
        }

        double consommations;
        try {
            consommations = Double.parseDouble(consommationsField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le montant des consommations est mal renseigné.");
            return this.showSejour(true);
        }

        Sejour sejour = new Sejour(beginDate, endDate, reservation.getChambre());
        sejour.ajouterConsommation(consommations);

        return sejour;
    }
}
