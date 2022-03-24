package controller;

import model.*;
import view.ChambreListView;

import java.util.ArrayList;
import java.util.Random;

public class ChambreListController {
    private static int id = 2;

    private Hotel model;
    private ChambreListView view;

    public ChambreListController(Hotel model, ChambreListView view) {
        this.model = model;
        this.view = view;

        view.setupEvents(this);
        view.modelUpdated(model.getChambres());
    }

    public void addChambre(int numero, int etage, double prix, ChambreType type) {
        switch (type) {
            case CHAMBRE_SIMPLE -> model.ajouterChambre(new ChambreSimple(model, numero, etage, prix));
            case CHAMBRE_DOUBLE -> model.ajouterChambre(new ChambreDouble(model, numero, etage, prix));
            case SUITE_SIMPLE -> model.ajouterChambre(new SuiteSimple(model, numero, etage, prix));
            case SUITE_PRESIDENTIELLE -> model.ajouterChambre(new SuitePresidentielle(model, numero, etage, prix));
        }

        view.modelUpdated(model.getChambres());
    }

    public void deleteChambre(int index) {
        model.getChambres().remove(index);

        view.modelUpdated(model.getChambres());
    }
}
