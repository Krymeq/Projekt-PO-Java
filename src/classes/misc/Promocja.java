package classes.misc;

import java.util.Random;

/**
 *
 * @author Mateusz Ksok
 */
public class Promocja 
{
    private final int obnizka;
    private int czasTrwania;    //ile dni promocji zostało
    /**
     * Konstruktor klasy promocja przypisujący jej losowy czas trwania z przedziału
     * od 2 do 31 dni oraz losową wartość obniżki pomiędzy 5 a 50%.
     */
    public Promocja() 
    {
        Random rnd = new Random();
        czasTrwania = rnd.nextInt(29) + 2;                  
        obnizka = rnd.nextInt(46) + 5;
    }
    /**
     * Funkcja zwracająca informacje o danej promocji jako string.
     * @return String zawierający dane promocji.
     */
    public String getString()
    {
        String output = "";
        
        output += "Procent obniżki: " + Integer.toString(obnizka) +
                  "\nPozostało dni:" + Integer.toString(czasTrwania);
        return output;
    }
    
    /**
     * Getter zwracający wartość obniżki (w procentach, jako liczbę całkowitą).
     * Dla przykładu - obnizka == 50 oznacza 50% upustu na produkty
     * objęte promocją.
     * @return Obnizka - procentowa wartość promocji.
     */
    public int getObnizka() 
    {
        return obnizka;
    }
    
    /**
     * Zwraca pozostałe dni trwania promocji.
     * @return Dni pozostałe do końca promocji
     */
    public int getCzasTrwania() 
    {
        return czasTrwania;
    }
    /**
     * Funkcja ustawia pozostały czas trwania promocji na podany jako
     * parametr.
     * @param i - Nowy czas trwania promocji.
     */
    public void setCzasTrwania(int i) {
        this.czasTrwania = i;
    }
    
}
