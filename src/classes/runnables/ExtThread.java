package classes.runnables;

import classes.misc.Symulacja;

/**
 * 
 * @author Kiosku
 */
public class ExtThread extends Thread
{
    private final Byt obiekt;
    private final Symulacja matrix;
    private volatile boolean akcja;
    private volatile boolean czySkonczyc;
    
    /**
     * Konstruktor klasy ExtThread, przypisujący polom wartości parametrów,
     * a flagom - false. Oznacza to, że wątek wykonywany będzie dopiero, 
     * gdy zostanie wywołana funkcja setAkcja(true) na tym obiekcie.
     * @param podpietyObiekt - obiekt, który będzie wykonywać wątek
     * @param sim - symulacja, w ramach której wykonujemy akcję
     */
    public ExtThread(Byt podpietyObiekt, Symulacja sim) 
    {
        this.matrix = sim;
        this.obiekt = podpietyObiekt;
        this.akcja = false;
        this.czySkonczyc = false;
    }
    /**
     * Przeciążona funkcja run - współbieżne wykonywanie wątków.
     * Nieskończona pętla, którą można jednak przerwać zmieniając
     * flagę czySkonczyc tej klasy. Do tego akcja jest wywoływana
     * w momencie, gdy flaga "akcja" danego obiektu jest ustawiona
     * na true - można w każdym momencie "uśpić" wątek zmieniając 
     * tą flagę na false. 
     */
    
    @Override
    public void run() 
    {
        
        while(czySkonczyc == false)
        {
            if (akcja)
            {
                obiekt.performAction(matrix);
                akcja = false;
            }
            try 
            {
                Thread.sleep(200);
            } 
            catch (InterruptedException ex) 
            {
                System.out.println(ex.getMessage() + this.getByt().toString());
            }
        }
    }
      
    /**
     * Funkcja zwracająca byt przypięty do tej klasy.
     * @return Byt przypięty do klasy i wykonujący w ramach wątku
     * funkcje.
     */
    public Byt getByt()
    {
        return this.obiekt;
    }
    
    /**
     * Funkcja zwracająca flagę akcja danego wątku.
     * True - wątek działa
     * False - wątek pauzuje.
     * @return Wartosc flagi akcja dla danego wątku.
     */
    public boolean isAkcja() {
        return akcja;
    }
    
    /**
     * Funkcja zwracająca flagę czySkonczyc danego wątku.
     * False - wątek działa, nie jest zakończony
     * True - wątek kończy ostatnią iterację pętli i się kończy.
     * @return Wartość flagi czySkonczyc danego wątku.
     */
    public boolean isCzySkonczyc() {
        return czySkonczyc;
    }
    
    /**
     * Funkcja pauzująca bądź budząca wątek.
     * True - wątek działa
     * False - wątek pauzuje.
     * @param akcja Komenda wysyłana do wątku.
     */
    public void setAkcja(boolean akcja) {
        this.akcja = akcja;
    }
    
    /**
     * Setter wysyłający sygnał kończenia do wątku.
     * True - wątek się kończy
     * False - wątek działa bez zmian.
     * @param czySkonczyc - true jeśli koniec, w przeciwnym wypadku false.
     */
    public void setCzySkonczyc(boolean czySkonczyc) {
        this.czySkonczyc = czySkonczyc;
    }

    @Override
    public String toString() {
        return this.obiekt.toString();
    }
}
