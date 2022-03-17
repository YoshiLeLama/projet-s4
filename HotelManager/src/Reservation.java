import java.util.Date;

public class Reservation {
    private static int idCnt;

    private int id;
    private Date debut;
    private Date fin;

    private Client client;
    private Chambre chambre;

    public Reservation(Date debut, Date fin, Client client, Chambre chambre) {
        id = idCnt++;
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.chambre = chambre;
        chambre.ajouterReservation(this);
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

    public Chambre getChambre() {
        return chambre;
    }
}
