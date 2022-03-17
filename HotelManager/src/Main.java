import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Hotel h = new Hotel();
        Chambre c = new ChambreSimple(1, 1, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Reservation r = new Reservation(sdf.parse("2022/12/12"), sdf.parse("2022/12/15"), null, c);
            System.out.println(c.disponible(sdf.parse("2022/12/16"), sdf.parse("2022/12/23")));
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
        }


    }
}
