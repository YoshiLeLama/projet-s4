package view;

import controller.ClientListController;
import model.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ClientListView extends JPanel {
    private final JList<Object> clientsList;

    private final JButton addClientButton;
    private final JButton deleteClientButton;
    private final JButton modifyClientButton;

    private final JTextField clientNomField;

    public ClientListView() {
        setLayout(new BorderLayout());

        JPanel addClientPanel = new JPanel(new FlowLayout());
        addClientPanel.add(new JLabel("Nom du client "));

        clientNomField = new JTextField();
        clientNomField.setPreferredSize(new Dimension(200, 20));
        addClientPanel.add(clientNomField);

        addClientButton = new JButton("Ajouter");
        addClientPanel.add(addClientButton);

        add(addClientPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        modifyClientButton = new JButton("Modifier le client");
        modifyClientButton.setEnabled(false);
        bottomPanel.add(modifyClientButton);

        deleteClientButton = new JButton("Supprimer le client");
        deleteClientButton.setEnabled(false);
        bottomPanel.add(deleteClientButton);

        add(bottomPanel, BorderLayout.SOUTH);

        clientsList = new JList<>();
        clientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientsList.setLayoutOrientation(JList.VERTICAL);
        clientsList.setVisibleRowCount(-1);

        clientsList.addListSelectionListener(e -> {
            boolean filled = !clientsList.isSelectionEmpty();
            modifyClientButton.setEnabled(filled);
            deleteClientButton.setEnabled(filled);
        });

        JScrollPane clientsListScroll = new JScrollPane(clientsList);
        clientsListScroll.getVerticalScrollBar().setUnitIncrement(16);
        clientsListScroll.setViewportView(clientsList);

        add(clientsListScroll, BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setupEvents(ClientListController controller) {
        addClientButton.addActionListener(e -> {
            if (clientNomField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le nom du client ne peut pas être vide");
                return;
            }

            controller.addClient(clientNomField.getText());
            clientNomField.setText("");
        });

        modifyClientButton.addActionListener(e -> {
            if (clientsList.isSelectionEmpty())
                return;

            String newNom = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this), "Entrez le nouveau nom du client");
            if (newNom.isEmpty()) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le nom ne peut pas être vide");
                return;
            }

            controller.modifyClient(clientsList.getSelectedIndex(), newNom);

            clientsList.requestFocus();
        });

        deleteClientButton.addActionListener(e -> {
            if (clientsList.isSelectionEmpty()) return;

            controller.deleteClient(clientsList.getSelectedIndex());
        });
    }

    public void modelUpdated(ArrayList<Client> model) {
        clientsList.setListData(model.toArray());
    }
}
