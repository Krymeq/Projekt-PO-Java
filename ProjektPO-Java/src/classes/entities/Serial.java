package classes.entities;

import classes.runnables.Dystrybutor;
import classes.misc.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * @author Mateusz Ksok
 */
public class Serial extends Product
{
    private final Gatunek gatunek;
    private final ArrayList<String> listaAktorow;
    private final ArrayList<ArrayList<Odcinek>> odcinki;
    /**
     * Konstruktor serialu losujący wartości pól i przypisujący 
     * konkretnego dystrybutora dla nowego produktu. Oprócz tego 
     * generuje dla serialu odcinki i sezony.
     * @param distro - Dystrybutor, który wypuścił dany serial.
     */
    public Serial(Dystrybutor distro)
    {
       super(distro); 
       String temp = new String();
       gatunek = Gatunek.getRandomGenre();
       listaAktorow = Utilities.generateActors();
       odcinki = Utilities.generateSeasons();
       cena = Math.round((new Random().nextInt(21)/2.0f + 9.99) * 100.0) / 100.0;
       
       for(String i: listaAktorow)
       {
           temp += "- " + i + "\n";
       }
       
       int sum = 0;
       int amount = 0;
       for (ArrayList<Odcinek> list: odcinki)
       {
           for (Odcinek x : list)
           {
               sum += x.getDlugosc();
               amount++;
           }
       }
       
       czasTrwania = sum / amount;
       opis = opis +  "Gatunek:\n" + gatunek.toString() + 
               "\nŚredni czas trwania odcinka:\n"+ Integer.toString(czasTrwania)+
               "\nAktorzy:\n" + temp;
    }
    /**
     * Getter zwracający reprezentację sezonów jako ciągu znaków.
     * @return String z wszystkimi 
     */
    public String getSeasonsAsString()
    {
        String output = new String();
        for (int i = 0; i < odcinki.size(); i++)
        {
            output += "-- Sezon " + Integer.toString(i+1) + "\n";
            for (int j = 0; j < odcinki.get(i).size();j++)
            {
                output += "-- Odcinek " + Integer.toString(j+1) + "\nData premiery: " +
                odcinki.get(i).get(j).getDataPremiery().toString() + "\nCzas trwania"
                   + " odcinka: " +Integer.toString(odcinki.get(i).get(j).getDlugosc())
                   + " min.\n";
            }
        }
        return output;
    }
    /**
     * Funkcja zwracająca łączną ilość obejrzeń wszystkich odcinków.
     * @return ilość obejrzeń wszystkich odcinków jako int
     */
    public int getTotalTimesWatched()
    {
        int sum = 0;
        for (ArrayList<Odcinek> list : odcinki )
        {
            for (Odcinek x : list) 
            {
                sum += x.getObejrzeniaDzis();
            }
        }
        return sum;
    }
    /**
     * Funkcja zwracająca losowy odcinek serialu 
     * (y - ty element z x - tej listy w ogólnej liście).
     * @return Losowy odcinek danego serialu.
     */
    public Odcinek getRandomEp()
    {
        Random rnd = new Random();
        int x = rnd.nextInt(odcinki.size());
        int y = rnd.nextInt(odcinki.get(x).size());
        
        return odcinki.get(x).get(y);
    }
    /**
     * Funkcja zwracająca listę aktorów występujących
     * w danym serialu.
     * @return ArrayList(String) z aktorami grającymi w serialu.
     */
    public ArrayList<String> getListaAktorow() {
        return listaAktorow;
    }   
}
