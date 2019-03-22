package classes.misc;

import classes.runnables.Dystrybutor;
import java.util.Random;

/**
 * @author Mateusz Ksok
 */
public class Umowa 
{
    private final Dystrybutor dystryb;
    private final Platforma platform;
    private boolean ryczalt;
    private double wartosc;
    
    /**
     * Konstruktor generuje umowę pomiędzy danym
     * dystrybutorem a platformą podanymi jako parametry
     * @param dx Strona umowy będąca dystrybutorem
     * @param px Strona umowy będąca platformą
     */
    public Umowa(Dystrybutor dx, Platforma px) 
    {
        Random rnd = new Random();
        
        this.dystryb = dx;
        this.platform = px;
        this.ryczalt = rnd.nextBoolean();
        
        if (ryczalt)                                           //ZMIEŃ CYFERKI!
        {
            this.wartosc = rnd.nextInt(501)/10.0 + 100.0;
        }
        else
        {
            this.wartosc = rnd.nextInt(20)/10.0 + 1;
        }
    }
    
    /**
     * Getter, który pozwala określić, czy dana umowa została zawarta
     * w formie ryczałtu.
     * @return Wartość logiczna true (płatność ryczałtem) bądź false (płatność 
     * od obejrzenia)
     */
    public boolean isRyczalt() {
        return ryczalt;
    }
    /**
     * Zmień typ umowy.
     *  true - ryczałt
     *  false - za obejrzenie.
     * @param ryczalt - wartość logiczna określająca typ umowy.
     */
    public void setRyczalt(boolean ryczalt) {
        this.ryczalt = ryczalt;
    }
    /**
     * Getter, zwracający wartość umowy
     * @return Zmienna typu double o wartości równiej wartości umowy.
     */
    public double getWartosc() {
        return wartosc;
    }
    
    /**
     * Ustawienie wartości umowy na nową, konkretną wartość
     * @param wartosc - nowa wartość umowy.
     */
    public void setWartosc(double wartosc) 
    {
        this.wartosc = wartosc;
    }
    
    /**
     * Funkcja zwracająca dustrybutora, z którym została zawarta umowa.
     * @return Zmienna typu Dystrybutor powiązana z daną umową.
     */
    public Dystrybutor getDystryb() {
        return dystryb;
    }
    
    /**
     * Funkcja zwracająca platformę, z którą została zawarta umowa.
     * @return Zmienna typu Platorma powiązana z daną umową.
     */
    public Platforma getPlatform() {
        return platform;
    }
}
