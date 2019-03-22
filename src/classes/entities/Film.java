package classes.entities;

import classes.runnables.Dystrybutor;
import classes.misc.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mateusz Ksok
 */
public class Film extends Product 
{
    private ArrayList<String> listaAktorow;
    private final String link;
    private int czasDostepu;
    private final Gatunek gatunek;
    
    /**
     * Konstruktor klasy Film. 
     * Przypisuje wszystkim polom wylosowane wartości
     * @param distro - Dystrybutor konkretnego filmu.
     */
    public Film(Dystrybutor distro) 
    {
        super(distro);
        String temp = new String();
        Random rn = new Random();
        link = Utilities.generateLink();
        cena = Math.round((rn.nextInt(21)/2.0f + 9.99) * 100.0) / 100.0;
        czasDostepu = 5; //dni
        czasTrwania = (new Random()).nextInt(30) + 105;
        listaAktorow = Utilities.generateActors();
        gatunek = Gatunek.getRandomGenre();
        for(String i: listaAktorow)
        {
            temp += "- " + i + "\n";
        }
        
        opis = opis +"Czas trwania: " + Integer.toString(czasTrwania/60) +"h, " + 
                Integer.toString(czasTrwania % 60) + "min.\n"+
                "Gatunek:\n" + gatunek.toString() +
                "\nAktorzy:\n"+ temp + "Link: " + link + "\nCena: ";
        listaAktorow = Utilities.generateActors();
    }
    /**
     * Getter dla linku do danego filmu
     * @return link do filmu jako String.
     */
    public String getLink() 
    {
        return link;
    }   

    /**
     * Getter dla startowych dni dostępu do filmu
     * @return Czas dostępu jako int.
     */
    public int getCzasDostepu() {
        return czasDostepu;
    }

    /**
     * Getter zwracający listę aktorów grających w danym filmie.
     * @return Lista aktorów jako ArrayList.
     */
    public ArrayList<String> getListaAktorow() {
        return listaAktorow;
    }
}