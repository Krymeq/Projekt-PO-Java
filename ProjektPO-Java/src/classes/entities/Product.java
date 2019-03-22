package classes.entities;

import classes.runnables.Dystrybutor;
import java.util.Date;
import java.util.ArrayList;
import classes.misc.*;

/**
 *
 * @author Kiosku
 */
public class Product 
{

    /**
     * Nazwa danego produktu.
     */
    private String nazwa;

    /**
     * Opis produktu.
     */
    protected String opis;
    
    /**
     * Cena produktu.
     */
    protected volatile double cena;

    /**
     * Ocena produktu od 1 do 10
     */
    private double ocena;     /*od 1 do 10*/

    /**
     * Czas trwania produktu (w przypadku seriali: średni czas 
     * trwania odcinka)
     */
    protected int czasTrwania;  /*minuty*/

    /**
     * Unikalne ID produktu
     */
    private final int id;

    /**
     * Ilość obejrzeń danego produktu.
     */
    private int obejrzenia;

    /**
     * Statyczna zmienna równa ID następnego produktu.
     */
    private static int nextId = 10000;
    
    /**
     * Data produkcji danego produktu.
     */
    private final Date dataProdukcji;

    /**
     * Dystrybutor danego produktu.
     */
    private final Dystrybutor dystr;

    /**
     * Kraje, w których wyprodukowano dany produkt.
     */
    private final ArrayList<Kraj> krajeProdukcji;

    /**
     * Konstruktor produktu - klasy bazowej dla trzech pozostałych - 
     * losujący wartości pól i przypisujący konkretnego dystrybutora 
     * dla nowego produktu.
     * @param distr - Dystrybutor, który wypuścił dany produkt.
     */
    public Product(Dystrybutor distr) 
    {
        obejrzenia = 0;
        dataProdukcji = Utilities.generateTime(1970, 2018).getTime();
        nazwa = Utilities.generateName();
        ocena = Utilities.generateRating();
        krajeProdukcji = Utilities.generateCountries();
        opis = Utilities.generateDesc
                (nazwa, 
                this.getClass().getSimpleName(),
                dataProdukcji,
                ocena,
                krajeProdukcji);
        id = nextId;
        dystr = distr;
        nextId++;
    }
    /**
     * Funkcja zwracająca unikalny numer ID dla danego produktu.
     * @return Unikalny identyfikator produktu
     */
    public int getId() {
        return id;
    }
    
    /**
     * Getter zwracający ocenę danego produktu.
     * @return Ocena danego obiektu typu produkt.
     */
    public double getOcena() {
        return ocena;
    }

    /**
     * Setter ustawiający nową ocenę obiektu, jeśli funkcja
     * została wywołana z parametrem z przedziału od 1 do 10 (włącznie).
     * @param ocena - nowa ocena produktu z przedziału od 1 do 10 (włącznie)
     */
    
    public void setOcena(double ocena) {
        if (ocena >= 1 && ocena <= 10)
        this.ocena = ocena;
    }
    
    /**
     * Getter zwracający czas trwania produktu (bądź w przypadku serialu -
     * średniego czasu trwania odcinka)
     * @return Czas trwania danego bytu w minutach.
     */
    
    public int getCzasTrwania() {
        return czasTrwania;
    }
    
    /**
     * Setter czasu trwania produktu - zmienia czas trwania
     * na ten podany jako parametr.
     * @param czasTrwania Nowy czas trwania odcinka w minutach.
     */
    
    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }
    
    
    /**
     * Getter zwracający opis danego bytu jako ciąg znaków - string.
     * @return Opis - zmienna typu string zawierająca opis danego
     * bytu.
     */
    public String getOpis()
    {
        return this.opis + "\n ID produktu: " +
                Integer.toString(id) + "\nCena: " + Double.toString(cena) + " zł\n" +
                "Obejrzenia: " + Integer.toString(obejrzenia);
    }
    
    /**
     * Getter zwracający dystrybutora obecnego produktu.
     * @return Dystrybutor obecnego produktu.
     */
    public Dystrybutor getDystr() {
        return dystr;
    }
    /**
     * Getter zwracający cenę obecnego produktu.
     * @return Cena obecnego produktu
     */
    public double getCena()
    {
        return this.cena;
    }
    /**
     * Setter dla ceny produktu.
     * @param cena - nowa cena obecnego produktu.
     */
    public void setCena(double cena) {
        if (cena > 0.00)
            this.cena = cena;
    }
    /**
     * Funkcja zwracająca ilość obejrzeń danego produktu
     * @return Obejrzenia danego produktu.
     */
    public int getObejrzenia() {
        return obejrzenia;
    }
    /**
     * Funkcja ustawiająca obejrzenia danego produktu na
     * wartość podaną jako parametr.
     * @param obejrzenia nowa ilość obejrzeń jako int.
     */
    public void setObejrzenia(int obejrzenia) {
        this.obejrzenia = obejrzenia;
    }
    
    /**
     * Funkcja toString zwracająca nazwę produktu.
     * Metoda ta służy również jako getter dla pola Nazwa - 
     * string zwracany zawiera tylko to pole.
     * @return String - nazwa produktu.
     */
    @Override
    public String toString() {
        return nazwa;
    }
}
