import java.util.ArrayList;
import java.util.Date;

public abstract class Chambre {
    private int numero;
    private int etage;
    private double prix;

    private ArrayList<Reservation> reservations;
    private ArrayList<Sejour> sejours;

    public Chambre(int num, int e, double p) {
        numero = num;
        etage = e;
        prix = p;
        reservations = new ArrayList<>();
        sejours = new ArrayList<>();
    }

    public boolean disponible(Date debut, Date fin) {
        for (Reservation r : reservations) {
            if ((fin.after(r.getDebut()) && fin.before(r.getFin())) || (debut.after(r.getDebut()) && debut.before(r.getFin())))
                return false;
        }

        return true;
    }

    public int getNumero() {
        return numero;
    }

    public void setPrix(double p) {
        prix = p;
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public double getPrix() {
        return prix;
    }
}
