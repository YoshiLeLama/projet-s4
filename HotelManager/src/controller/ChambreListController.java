package controller;

import model.Chambre;
import model.ChambreSimple;
import model.ChambreType;
import view.ChambreListView;

import java.util.ArrayList;
import java.util.Random;

public class ChambreListController {
    private static int id = 2;

    private ArrayList<Chambre> model;
    private ChambreListView view;

    public ChambreListController(ArrayList<Chambre> model, ChambreListView view) {
        this.model = model;
        this.view = view;

        view.setupEvents(this);
        view.modelUpdated(model);
    }

    public void addChambre(int numero, int etage, double prix, ChambreType type) {
        model.add(new ChambreSimple(null, id++, etage, Math.floor((Math.random() + 1.0) * 60)));

        view.modelUpdated(model);
    }
}
