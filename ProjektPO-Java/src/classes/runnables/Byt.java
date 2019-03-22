package classes.runnables;

import classes.misc.Symulacja;
/**
 * 
 * @author Mateusz Ksok
 */
public interface Byt 
{
    /**
     * Abstrakcyjna metoda, w której klasy dziedziczące 
     * implementują akcje wykonane w ramach symulacji.
     * @param sim - Symulacja, w ramach których dany byt wykona
     * akcję.
     */
    public void performAction(Symulacja sim);
    
    /**
     * Funkcja zwracająca informację o danym bycie na potrzeby
     * wyświetlenia ich przez GUI.
     * @return String z podstawowymi danymi o bycie.
     */
    public String returnInfo();
}
