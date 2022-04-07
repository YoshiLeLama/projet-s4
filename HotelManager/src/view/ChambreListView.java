package view;

import controller.ChambreListController;
import model.Chambre;
import model.ChambreType;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChambreListView extends JPanel {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final JButton addChambreButton;
    private final JButton modifyChambreButton;
    private final JButton deleteChambreButton;
    private final JList<Object> chambresList;

    // Elements permettant d'ajouter une chambre
    private final JTextField numeroTextField;
    private final JTextField etageTextField;
    private final JTextField prixTextField;

    private final JComboBox<ChambreType> chambreTypeCombo;
    private static final ChambreType[] chambreTypes = {
            ChambreType.CHAMBRE_SIMPLE,
            ChambreType.CHAMBRE_DOUBLE,
            ChambreType.SUITE_SIMPLE,
            ChambreType.SUITE_PRESIDENTIELLE
    };
    private final JTextField beginDateField;
    private final JTextField endDateField;
    private final JButton filterButtonField;

    public ChambreListView() {
        setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new FlowLayout());

        modifyChambreButton = new JButton("Modifier la chambre");
        modifyChambreButton.setEnabled(false);
        bottomPanel.add(modifyChambreButton);

        deleteChambreButton = new JButton("Supprimer la chambre");
        deleteChambreButton.setEnabled(false);
        bottomPanel.add(deleteChambreButton);

        add(bottomPanel, BorderLayout.SOUTH);

        chambresList = new JList<>();
        chambresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chambresList.setLayoutOrientation(JList.VERTICAL);
        chambresList.setVisibleRowCount(-1);

        chambresList.addListSelectionListener(e -> {
            boolean filled = !chambresList.isSelectionEmpty();
            modifyChambreButton.setEnabled(filled);
            deleteChambreButton.setEnabled(filled);
        });

        JScrollPane chambreListScroll = new JScrollPane(chambresList);
        chambreListScroll.getVerticalScrollBar().setUnitIncrement(16);
        chambreListScroll.setViewportView(chambresList);

        add(chambreListScroll, BorderLayout.CENTER);

        JPanel addChambrePanel = new JPanel(new FlowLayout());

        addChambrePanel.add(new JLabel("Numéro "));

        numeroTextField = new JTextField();
        numeroTextField.setToolTipText("Numéro de la chambre");
        numeroTextField.setPreferredSize(new Dimension(50, 20));
        addChambrePanel.add(numeroTextField);

        addChambrePanel.add(new JLabel(" Étage "));

        etageTextField = new JTextField();
        etageTextField.setToolTipText("Numéro d'étage");
        etageTextField.setPreferredSize(new Dimension(50, 20));
        addChambrePanel.add(etageTextField);

        addChambrePanel.add(new JLabel(" Prix "));

        prixTextField = new JTextField();
        prixTextField.setToolTipText("Prix de la nuit");
        prixTextField.setPreferredSize(new Dimension(50, 20));
        addChambrePanel.add(prixTextField);

        chambreTypeCombo = new JComboBox<>(chambreTypes);
        addChambrePanel.add(chambreTypeCombo);

        addChambreButton = new JButton("Ajouter");
        addChambrePanel.add(addChambreButton);

        addChambrePanel.setPreferredSize(new Dimension(1000, 60));

        add(addChambrePanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        leftPanel.add(new JLabel("Date de début"));

        beginDateField = new JTextField();
        beginDateField.setMaximumSize(new Dimension(200, 20));
        leftPanel.add(beginDateField);

        leftPanel.add(new JLabel("Date de fin"));

        endDateField = new JTextField();
        endDateField.setMaximumSize(new Dimension(200, 20));
        leftPanel.add(endDateField);

        filterButtonField = new JButton("Filtrer");
        leftPanel.add(filterButtonField);

        add(leftPanel, BorderLayout.WEST);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setupEvents(ChambreListController controller) {
        addChambreButton.addActionListener(e -> {
            int numero;
            try {
                numero = Math.abs(Integer.parseInt(numeroTextField.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le numéro de chambre est invalide");
                return;
            }

            int etage;
            try {
                etage = Integer.parseInt(etageTextField.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le numéro d'étage est invalide");
                return;
            }

            double prix;
            try {
                prix = Math.abs(Double.parseDouble(prixTextField.getText()));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le prix est invalide");
                return;
            }

            if (!controller.addChambre(
                    numero,
                    etage,
                    prix,
                    chambreTypes[chambreTypeCombo.getSelectedIndex()])) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Une chambre a déjà le numéro " + numero);
                return;
            }

            numeroTextField.setText("");
            etageTextField.setText("");
            prixTextField.setText("");
        });

        modifyChambreButton.addActionListener(e -> {
            if (chambresList.isSelectionEmpty())
                return;

            String newPrix = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this), "Nouveau prix de la chambre");
            double prix;
            try {
                prix = Double.parseDouble(newPrix);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Le prix entré est invalide");
                return;
            }

            controller.modifyChambre(chambresList.getSelectedIndex(), prix);

            chambresList.requestFocus();
        });

        deleteChambreButton.addActionListener(e -> {
            if (!chambresList.isSelectionEmpty())
                controller.deleteChambre(chambresList.getSelectedIndex());
        });

        filterButtonField.addActionListener(e -> {
            // TODO Ajout d'un système de filtre en fonction de dates pour récupérer les chambres disponibles
            Date beginDate, endDate;
            try {
                beginDate = dateFormat.parse(beginDateField.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "La date de début de la réservation est invalide");
                return;
            }

            try {
                endDate = dateFormat.parse(endDateField.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "La date de fin de la réservation est mal renseignée");
                return;
            }

            controller.availableChambres(beginDate, endDate);
            beginDateField.setText("");
            endDateField.setText("");
        });
    }

    public void modelUpdated(ArrayList<Chambre> model) {
        chambresList.setListData(model.toArray());

        revalidate();
    }
}
