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
public enum Kraj {

    /**
     *
     */
    Polska,

    /**
     *
     */
    Czechy,

    /**
     *
     */
    Niemcy,

    /**
     *
     */
    Francja,

    /**
     *
     */
    Rosja,

    /**
     *
     */
    Wielka_Brytania,

    /**
     *
     */
    USA,

    /**
     *
     */
    Indie,

    /**
     *
     */
    Włochy,

    /**
     *
     */
    Grecja,

    /**
     *
     */
    Węgry,

    /**
     *
     */
    Chiny,

    /**
     *
     */
    Japonia,

    /**
     *
     */
    Meksyk,

    /**
     *
     */
    Kanada,

    /**
     *
     */
    Hiszpania,

    /**
     *
     */
    Portugalia,

    /**
     *
     */
    Irlandia,

    /**
     *
     */
    Słowacja;
    
    /**
     *Funkcja zwracająca losową wartość spośród zbioru krajów.
     * @return Losowy kraj.
     */
    public static Kraj getRandomCountry() 
    {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
