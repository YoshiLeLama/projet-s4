package model;

import java.util.ArrayList;
import java.util.Date;

public class Hotel {
    private ArrayList<Chambre> chambres;

    public Hotel() {
        chambres = new ArrayList<>();
    }

    public ArrayList<Chambre> getChambres() {
        return chambres;
    }

    public void ajouterChambre(Chambre chambre) {
        if (chambre != null)
            chambres.add(chambre);
    }

    public ArrayList<Chambre> chambresDisponibles(Date debut, Date fin) {
        ArrayList<Chambre> chambresDisponibles = new ArrayList<>();
        for (Chambre chambre : chambres) {
            if (chambre.disponible(debut, fin))
                chambresDisponibles.add(chambre);
        }

        return chambresDisponibles;
    }

    public void modifierPrixChambre(int id, double prix) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumero() == id) {
                chambre.setPrix(prix);
                break;
            }
        }
    }

    public void supprimerChambre(int id) {
        for (int i = 0; i < chambres.size(); i++) {
            if (chambres.get(i).getNumero() == id) {
                chambres.remove(i);
                break;
            }
        }
    }
}
