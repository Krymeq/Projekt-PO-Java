
package classes.entities;

import java.util.*;

/**
 * Klasa symulująca pojedynczy odcinek serialu.
 * @author Mateusz Ksok
 */
public class Odcinek 
{
    private Date dataPremiery;
    private int dlugosc; //minuty 
    private int obejrzeniaDzis;
    
    /**
     * Konstruktor klasy Odcinek, tworzący pojedynczy odcinek o losowej
     * długości z przedziału 50-65 min i dacie premiery równej
     * parametrowi konstruktora.
     * @param cal - Zmienna typu Calendar zawierająca datę premiery odcinka.
     */
    public Odcinek(Calendar cal)
    {
        obejrzeniaDzis = 0;
        dataPremiery = cal.getTime();
        dlugosc = (new Random().nextInt(16) + 50);
    }
    
    /**
     * Getter zwracający datę premiery odcinka.
     * @return Zmienna typu Date zawierająca datę premiery odcinka.
     */
    public Date getDataPremiery() {
        return dataPremiery;
    }
    /**
     * Setter zmieniający datę premiery.
     * @param dataPremiery - nowa data premiery odcinka.
     */
    public void setDataPremiery(Date dataPremiery) {
        this.dataPremiery = dataPremiery;
    }
    
    /**
     * Getter zwracający długość odcinka w minutach.
     * @return Zmienna typu int zawierająca długość odcinka w minutach.
     */
    public int getDlugosc() {
        return dlugosc;
    }
    /**
     * Funkcja zmieniająca długość odcinka na podaną w parametrze.
     * @param dlugosc - Nowa długość odcinka jako int.
     */
    public void setDlugosc(int dlugosc) 
    {
        this.dlugosc = dlugosc;
    }
    /**
     * Funkcja zwracająca obejrzenia danego odcinka.
     * @return Ilość obejrzeń danego odcinka od początku symulacji.
     */
    public int getObejrzeniaDzis() {
        return obejrzeniaDzis;
    }
    /**
     * Funkcja ustawiająca ilość obejrzeń na równą parametrowi.
     * Używana do inkrementacji.
     * @param timesWatched - ilość obejrzeń danego odcinka.
     */
    public void setObejrzeniaDzis(int timesWatched) {
        this.obejrzeniaDzis = timesWatched;
    }
}
