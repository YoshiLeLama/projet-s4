import java.util.Date;

public class Client {
    private static int idCnt;

    private int id;
    private String nom;

    private Reservation reservation;
    private Sejour sejour;

    public Client(String n) {
        id = idCnt++;
        nom = n;
    }

    public void ajouterReservation(Date debut, Date fin, Chambre chambre) {
        reservation = new Reservation(debut, fin, this, chambre);
    }

    public void honorerSejour(Date debut, Date fin) {
        sejour = new Sejour(debut, fin, this, reservation.getChambre());
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client :\n id : " + id + "\n nom : " + nom + "\n reservation : " + reservation + "\n séjour : " + sejour + "\n";
    }
}
