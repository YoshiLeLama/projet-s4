import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public class Sejour {
    private double consommations;
    private Date debut;
    private Date fin;

    private Client client;
    private Chambre chambre;

    public Sejour(Date debut, Date fin, Client client, Chambre chambre) {
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.chambre = chambre;
    }

    public double facturer() {
        return ChronoUnit.DAYS.between((Temporal) fin, (Temporal) debut) * chambre.getPrix() + consommations;
    }

    public void ajouterConsommation(double prix) {
        consommations += prix;
    }

    public String toString() {
        return "Séjour :\n date de début : " + debut + "\n date de fin : " + fin + "\n prix des consommations : " + consommations + "\n";
    }
}
