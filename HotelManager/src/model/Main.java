package model;

public class Main {
    public static void main(String[] args) {
        run();
    }

    static void run() {
        Hotel h = new Hotel();
        for(int i = 0; i < 10; i++) {
            Chambre c = new ChambreSimple(2, 100);
            h.ajouterChambre(c);
        }

        h.afficher(true);
    }
}
