import java.util.ArrayList;
import java.util.Date;

public abstract class Chambre {
    private int id;
    private int etage;
    private double prix;

    private ArrayList<Reservation> reservations;

    boolean disponible(Date debut, Date fin) {
        return true;
    }
}
