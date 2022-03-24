package view;

import controller.ReservationListController;
import model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservationListView extends JPanel {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final JButton addReservationButton;
    private final JButton modifyReservationButton;
    private final JButton deleteReservationButton;
    private JList<Object> reservationsList;

    private final JTextField reservationBeginDateField;
    private final JTextField reservationEndDateField;
    private final JTextField clientIdField;
    private final JTextField numeroChambreField;

    public ReservationListView() {
        setLayout(new BorderLayout());

        JPanel addReservationPanel = new JPanel(new FlowLayout());

        addReservationPanel.add(new JLabel("Date de début "));

        reservationBeginDateField = new JTextField();
        reservationBeginDateField.setPreferredSize(new Dimension(100, 20));
        addReservationPanel.add(reservationBeginDateField);

        addReservationPanel.add(new JLabel("Date de fin "));

        reservationEndDateField = new JTextField();
        reservationEndDateField.setPreferredSize(new Dimension(100, 20));
        addReservationPanel.add(reservationEndDateField);

        addReservationPanel.add(new JLabel("Client "));

        clientIdField = new JTextField();
        clientIdField.setPreferredSize(new Dimension(100, 20));
        addReservationPanel.add(clientIdField);

        addReservationPanel.add(new JLabel("Chambre "));

        numeroChambreField = new JTextField();
        numeroChambreField.setPreferredSize(new Dimension(100, 20));
        addReservationPanel.add(numeroChambreField);

        addReservationButton = new JButton("Ajouter");
        addReservationPanel.add(addReservationButton);

        add(addReservationPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        modifyReservationButton = new JButton("Modifier le client");
        modifyReservationButton.setEnabled(false);
        bottomPanel.add(modifyReservationButton);

        deleteReservationButton = new JButton("Supprimer le client");
        deleteReservationButton.setEnabled(false);
        bottomPanel.add(deleteReservationButton);

        add(bottomPanel, BorderLayout.SOUTH);

        reservationsList = new JList<>();
        reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reservationsList.setLayoutOrientation(JList.VERTICAL);
        reservationsList.setVisibleRowCount(-1);

        reservationsList.addListSelectionListener(e -> {
            boolean filled = !reservationsList.isSelectionEmpty();
            modifyReservationButton.setEnabled(filled);
            deleteReservationButton.setEnabled(filled);
        });

        JScrollPane reservationsListScroll = new JScrollPane(reservationsList);
        reservationsListScroll.getVerticalScrollBar().setUnitIncrement(16);
        reservationsListScroll.setViewportView(reservationsList);

        add(reservationsListScroll, BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setupEvents(ReservationListController controller) {
        addReservationButton.addActionListener(e -> {
            Date beginDate;
            try {
                beginDate = dateFormat.parse(reservationBeginDateField.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "La date de début de la réservation est invalide");
                return;
            }

            Date endDate;
            try {
                endDate = dateFormat.parse(reservationEndDateField.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "La date de fin de la réservation est mal renseignée");
                return;
            }

            if (clientIdField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "L'identifiant du client ne peut pas être nul");
                return;
            }

            int clientId;
            try {
                clientId = Math.abs(Integer.parseInt(clientIdField.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "L'identifiant du client est invalide");
                return;
            }

            if (numeroChambreField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le numéro de chambre ne peut pas être nul");
                return;
            }

            int numeroChambre;
            try {
                numeroChambre = Math.abs(Integer.parseInt(numeroChambreField.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "L'identifiant du client est invalide");
                return;
            }

            if (!controller.addReservation(clientId, beginDate, endDate, numeroChambre)) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "L'ajout de la réservation a échoué");
                return;
            }

            reservationBeginDateField.setText("");
            reservationEndDateField.setText("");
            clientIdField.setText("");
            numeroChambreField.setText("");
        });

        deleteReservationButton.addActionListener(e -> {
            if (reservationsList.isSelectionEmpty())
                return;
            controller.deleteReservation(reservationsList.getSelectedIndex());
        });
    }

    public void modelUpdated(ArrayList<Reservation> reservations) {
        reservationsList.setListData(reservations.toArray());

        revalidate();
    }
}
