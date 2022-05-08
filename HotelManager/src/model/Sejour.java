package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public class Sejour {
    private double consommations;
    private final Date debut;
    private final Date fin;

    private final Chambre chambre;

    public Sejour(Date debut, Date fin, Chambre chambre) {
        if (debut == null || fin == null || chambre == null)
            throw new IllegalArgumentException("Les arguments du constructeur de model.Sejour ne peuvent pas être nuls");
        this.debut = debut;
        this.fin = fin;
        this.chambre = chambre;
    }

    public double facturer() {
        return Duration.between( debut.toInstant(), fin.toInstant()).toDays() * chambre.getPrix() + consommations;
    }

    public void ajouterConsommation(double prix) {
        consommations += prix;
    }

    public String toString() {
        return "Séjour :\n date de début : " + debut + "\n date de fin : " + fin + "\n prix des consommations : " + consommations + "\n";
    }
}
