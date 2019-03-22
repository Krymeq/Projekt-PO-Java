package classes.misc;

public class WpisBiblioteczny 
{
    /**
     * każdy produkt ma swoje unikalne ID, co można wykorzystać
     * aby zwracać konkretny element z listy...albo go zapamiętać.
     */
    
    private final int id;
    
    /**
     * Dni, przez które użytkownik może obejrzeć dany film.
     */
    private int pozostaleDni;
    
    /**
     * Konstruktor tworzący nowy wpis biblioteczny do bazy danych.
     * @param id ID produktu, którego dotyczy dany wpis
     * @param remDays Pozostałe dni, przez które klient ma dostęp do
     * danego produktu.
     */
    
    public WpisBiblioteczny(int id, int remDays)
    {
        this.id = id;
        this.pozostaleDni = remDays;
    }
    
    /**
     * Getter zwracający ID danego produktu
     * @return id;
     */
    public int getId() {
        return id;
    }
    
    /**
     * Funkcja zwracająca pozostałe dni, przez które wpis
     * pozostaje w systemie.
     * @return Pozostałe dni wpisu.
     */
    public int getPozostaleDni() {
        return pozostaleDni;
    }
    
    /**
     * Funkcja ustawiająca pozostałe dni, w trakcie których wpis 
     * pozostanie w systemie.
     * @param pozostaleDni Nowa wartość pozostałych dni w systemie.
     */
    public void setPozostaleDni(int pozostaleDni) {
        this.pozostaleDni = pozostaleDni;
    }
}
