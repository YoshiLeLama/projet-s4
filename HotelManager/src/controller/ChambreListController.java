package controller;

import java.util.ArrayList;
import java.util.Date;

import model.*;
import view.ChambreListView;

public class ChambreListController {
    private Hotel model;
    private ChambreListView view;

    public ChambreListController(Hotel model, ChambreListView view) {
        this.model = model;
        this.view = view;

        this.model.addOnReservationUpdateCallback(unused -> view.modelUpdated(model.getChambres()));

        view.setupEvents(this);
        view.modelUpdated(model.getChambres());
    }

    public boolean addChambre(int numero, int etage, double prix, ChambreType type) {
        for (Chambre chambre : model.getChambres()) {
            if (chambre.getNumero() == numero) {
                return false;
            }
        }

        switch (type) {
            case CHAMBRE_SIMPLE -> model.ajouterChambre(new ChambreSimple(model, numero, etage, prix));
            case CHAMBRE_DOUBLE -> model.ajouterChambre(new ChambreDouble(model, numero, etage, prix));
            case SUITE_SIMPLE -> model.ajouterChambre(new SuiteSimple(model, numero, etage, prix));
            case SUITE_PRESIDENTIELLE -> model.ajouterChambre(new SuitePresidentielle(model, numero, etage, prix));
        }

        view.modelUpdated(model.getChambres());

        return true;
    }

    public void modifyChambre(String value, double prix) {
        // model.getChambres().get(index).setPrix(newPrix);
        model.modifyChambre(value, prix);
        view.modelUpdated(model.getChambres());
    }

    public void deleteChambre(int index) {
        model.getChambres().remove(index);

        view.modelUpdated(model.getChambres());
    }

    public ArrayList<Chambre> availableChambres(Date debut, Date fin) {
        ArrayList<Chambre> chambresDispo = model.availableChambres(debut, fin);
        view.modelUpdated(chambresDispo);
        //return chambresDispo;
        return null;
    }
}
