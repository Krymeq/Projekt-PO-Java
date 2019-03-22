package classes.misc;

import java.util.LinkedList;

/**
 *
 * @author Mateusz Ksok
 */
public class Platforma 
{
    private Promocja promo;
    private volatile double saldo;
    private double saldoWczesniejsze;
    private int miesiaceStraty;
    private volatile LinkedList<Umowa> listaUmow;
    
    
    /**
     * Konstruktor nowej platformy, ustawiający wszystkie pola na zero.
     */
    public Platforma()
    {
        saldo = 0;
        saldoWczesniejsze = 0;
        miesiaceStraty = 0;
        promo = null;
        listaUmow = new LinkedList();
    }
    /**
     * Funkcja sprawdzająca czy nie ma straty w porównaniu do zapamiętanej
     * wartości. Jeśli taka jest to pole "miesiąceStraty" jest inkrementowane,
     * jeśli nie - zerowane. W obu przypadkach obecna wartość salda jest
     * zapamiętywana do dalszego porównania.
     * @return Miesiące, przez które symulacja była stratna.
     */
    public int sprawdzCzyStrata()
    {
        if (saldo - saldoWczesniejsze < 0)
            miesiaceStraty++;
        else miesiaceStraty = 0;
        saldoWczesniejsze = saldo;
        return miesiaceStraty;
    }
    /**
     * Funkcja ustawia nową promocję w systemie.
     * @param promo Nowa promocja.
     */
    public void setPromo(Promocja promo)
    {
        this.promo = promo;
    }
    
    /**
     * Getter zwracający obecne saldo platformy.
     * @return Zmienna typu double zawierająca saldo.
     */
    public synchronized double getSaldo() {
        return saldo;
    }

    /**
     * Setter ustalający nowe saldo konta platformy VOD.
     * @param saldo - wartość nowego salda konta platformy.
     */
    public synchronized void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Getter zwracajacy listę umów 
     * @return Lista umów z dystrybutorami zawarta
     * przez tą platformę.
     */
    public synchronized LinkedList<Umowa> getListaUmow() {
        return listaUmow;
    }
    
    /**
     * Zwraca ilość ostatnich miesięcy, w których platforma przynosiła straty.
     * @return Ilość ostatnich miesięcy straty pod rząd.
     */
    public int getMiesiaceStraty()
    {
        return this.miesiaceStraty;
    }
    
    /**
     * Funkcja zwracająca aktualną promocję, NULL jeśli żadnej nie ma.
     * @return Istniejąca promocja bądź jej brak (NULL)
     */
    public Promocja getPromo() {
        return promo;
    }
}
