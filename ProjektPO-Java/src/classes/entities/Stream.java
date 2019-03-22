package classes.entities;

import classes.runnables.Dystrybutor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * 
 * @author Mateusz Ksok
 */
public class Stream extends Product
{
    private final Date dataWyswietlenia;
    
    
    /**
     * Konstruktor klasy Stream imitującej wydarzenie emitowane jednorazowo.
     * Funkcja ta losuje wartości wszystkich pól poza datą emisji i dystrybutorem,
     * które to pobiera jako parametry do konstruktora.
     * @param streamDate - Parametr typu Date zawierający datę emisji wydarzenia.
     * @param distro - Dystrybutor odpowiedzialny za konkretną tramsmisję.
     */
    public Stream(Date streamDate, Dystrybutor distro)
    {               
        super(distro);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(streamDate);
        cal.add(Calendar.DAY_OF_YEAR, new Random().nextInt(60) + 30);
        this.dataWyswietlenia = cal.getTime();
        this.cena = Math.round((new Random().nextInt(21)/2.0f + 14.99) * 100.0) / 100.0;
        this.opis +="Data wyświetlenia: " + formatter.format(dataWyswietlenia);
    }
    /**
     * Funkcja zwracająca datę wyświetlenia.
     * @return Data emisji streamu jako obiekt 
     * klasy Date.
     */
    public Date getDataWyswietlenia() {
        return dataWyswietlenia;
    }
}
