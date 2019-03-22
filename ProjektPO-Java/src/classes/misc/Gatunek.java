/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.misc;

import java.util.Random;

/**
 *
 * @author Mateusz Ksok
 */
public enum Gatunek 
{

    /**
     *
     */
    sensacyjny,

    /**
     *
     */
    dramat,

    /**
     *
     */
    komedia,

    /**
     *
     */
    dla_dzieci,

    /**
     *
     */
    akcja,

    /**
     *
     */
    dokumentalny;
    
    /**
     * Losowy gatunek z danego zbioru.
     * @return Losowa wartość gatunku, który może 
     * przyjąć dany produkt.
     */
    public static Gatunek getRandomGenre() 
    {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
