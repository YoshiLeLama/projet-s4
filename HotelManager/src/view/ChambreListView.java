package view;

import controller.ChambreListController;
import model.Chambre;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChambreListView extends JScrollPane {
    private final JButton addChambreButton;
    private JPanel contentPanel;
    private JPanel chambresListPanel;

    private ArrayList<ChambreDetailsView> detailsViewList;

    public ChambreListView() {
        verticalScrollBar.setUnitIncrement(16);

        BorderLayout contentLayout = new BorderLayout();
        contentPanel = new JPanel(contentLayout);

        chambresListPanel = new JPanel();
        BoxLayout listLayout = new BoxLayout(chambresListPanel, BoxLayout.Y_AXIS);
        chambresListPanel.setLayout(listLayout);
        contentPanel.add(chambresListPanel, BorderLayout.CENTER);

        addChambreButton = new JButton("addChambre");
        contentPanel.add(addChambreButton, BorderLayout.NORTH);

        detailsViewList = new ArrayList<>();

        setViewportView(contentPanel);
        setVisible(true);
    }

    public void setupEvents(ChambreListController controller) {
        addChambreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addChambre(1, 1, 60, null);
            }
        });
    }

    public void modelUpdated(ArrayList<Chambre> model) {
        chambresListPanel.removeAll();

        chambresListPanel.add(addChambreButton);

        for (Chambre chambre : model) {
            System.out.println(chambre.getPrix());

            chambresListPanel.add(new JLabel("Chambre no. " + chambre.getNumero() +
                    ", étage no. " + chambre.getEtage() +
                    ", prix " + chambre.getPrix() + "€ par nuit"));
        }

        revalidate();
    }
}
