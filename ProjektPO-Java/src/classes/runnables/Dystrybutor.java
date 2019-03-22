/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.runnables;

import classes.entities.*;
import classes.misc.*;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Kiosku
 */
public class Dystrybutor implements Byt 
{
    private Umowa umowa;
    private volatile double saldo;
    private final int id;
    private static int nextId = 1000;
    
    /**
     * Konstruktor dystrybutora dający mu zerowe saldo oraz generujący dla
     * niego nową umowę.
     * @param po - Platforma, na którą dystrybutor będzie wypuszczał
     * produkty.
     */
    public Dystrybutor(Platforma po) 
    {
        id = nextId;
        umowa = new Umowa(this,po);
        saldo = 0.0f;
        nextId++;
    }
    
    /**
     * Nadpisanie funkcji performAction z interfejsu byt.
     * Metoda, w której klasy dziedziczące 
     * implementują akcje wykonane w ramach symulacji.
     * @param sim - Symulacja, w ramach których dany byt wykona
     * akcję.
     */
    @Override
    public void performAction(Symulacja sim) 
    {
        // Wybranie akcji do wykonania:
        // 0 - nie rób nic
        // 1 - renegocjuj umowę
        // 2 - wypuść coś
        Random rnd = new Random();
        int akcja = rnd.nextInt(3);
        int szansa;
        switch (akcja)
        {
            case 1:
                szansa = rnd.nextInt(5);
                if (szansa < 1)
                    this.renegocjujUmowe();
                break;
            case 2:
                szansa = rnd.nextInt(3);
                if (szansa < 1)
                {
                    Product nowy = this.wypuscProdukt();
                    sim.getListaProduktow().add(nowy);
            }
        }
    }
    
    /**
     * Funkcja przypisująca umowie wartość o maksymalnie 1/3 mniejszą
     * od obecnej z prawdopodobieństwem powodzenia 40%.
     */
    public void renegocjujUmowe()
    {
        Random rnd = new Random();
        if (rnd.nextInt(5) > 2)
        {
            double x = umowa.getWartosc();
            if (x >= 1)
                x -= (rnd.nextInt((int)x))/3.0;
            umowa.setWartosc(x);
        }
    }
    
    
    /**
     * Funkcja powodująca wypuszczenie przez obecny obiekt
     * dystrybutora nowego produktu losowo z jednego z trzech rodzajów:
     * filmu, streamu i serialu.
     * @return Nowy produkt wypuszczony przez danego dystrybutora.
     */
    public Product wypuscProdukt()
    {
        Product newPr;
        int type = new Random().nextInt(3);
        switch(type)
        {
            case 0:
                newPr = new Serial(this);
                break;
            case 1:
                newPr = new Film(this);
                break;
            default:
                newPr = new Stream(new Date(), this);
                break;
        }
        
        return newPr;
    }
    
    /**
     * Getter dla umowy
     * @return Umowa bieżącego dystrybutora
     */
    public Umowa getUmowa() {
        return umowa;
    }
    
    /**
     * Setter dla umowy
     * @param umowa Nowa umowa dla dystrybutora.
     */
    public void setUmowa(Umowa umowa) {
        this.umowa = umowa;
    }
    
    /**
     * Getter zarobków dystrybutora
     * @return Zmienna typu double równa zarobkom obecnego dystrybutora
     */
    public synchronized double getSaldo() {
        return saldo;
    }
    
    /**
     * Setter dla zarobków obecnego dystrybutora.
     * @param zarobki - nowa wartość salda konta dystrybutora.
     */
    
    public synchronized void setSaldo(double zarobki) 
    {
        this.saldo = zarobki;
    }
    
    /**
     * Nadpisanie funkcji toString() zwracające string identyfikujący
     * danego dystrybutora.
     * @return String według wzoru: "Dystrybutor $idDystrybutora"
     */
    @Override
    public String toString() {
        return ("Dystrybutor " + Integer.toString(id)) ;
    }
    
    /**
     * Nadpisanie funkcji returnInfo() z interfejsu Byt.
     * Metoda zwraca String zawierający nieco bardziej szczegółowe informacje
     * o sobie, takie jak: ID, saldo konta, typ umowy i jej wartość.
     * @return Funkcja zwracająca info o danym dystrybutorze.
     */
    @Override
    public String returnInfo() 
    {
        String opis = new String();
        opis += "ID dystrybutora: " + Integer.toString(this.id) +
                "\n Saldo konta: " + Double.toString(this.saldo);
    
        if (this.umowa.isRyczalt())
            opis += "\nZwiązany umową ryczałtową.";
        
        else
            opis += "\nZwiązany umową za wyświetlenie.";
        
        opis += "\nUmowa opiewa na: " + Double.toString(
                Math.round(this.umowa.getWartosc() * 100.0) / 100.0) + "zł.";
        return opis;
    }
}
