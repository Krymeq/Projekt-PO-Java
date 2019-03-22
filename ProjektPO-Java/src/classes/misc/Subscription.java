package classes.misc;

/**
 *
 * @author Kiosku
 */
public class Subscription 
{
    private String resolution;
    private String type;
    
    private double price;
    private int devices;
    
    private static double basicPrice = 19.99f;
    private static double familyPrice = 29.99f;
    private static double premiumPrice = 35.99f;
    /**
     * Konstruktor klasy Subscription - tworzy nowy obiekt Promocja, 
     * przypisywany do każdego klienta o typie zadanym jako parametr
     * wywołania.
     * @param mode 1 - tworzy promocję typu Basic
     *             2 - tworzy promocję typu Family
     *             3 - tworzy promocję typu Premium
     * Cokolwiek innego - tworzy darmową subskrypcję
     */
    public Subscription(int mode) 
    {
        switch (mode) 
        {
            case 1:
                type = "Basic";
                price = basicPrice;
                devices = 1;
                resolution = "1080p FullHD";
                break;
            case 2:
                type = "Family";
                price = familyPrice;
                devices = 3;
                resolution = "1080p FullHD";
                break;
            case 3:
                type = "Premium";
                price = premiumPrice;
                devices = 5;
                resolution = "4k Ultra HD";
                break;
            default:
                type = "None";
                price = 0.00f;
                devices = 1;
                resolution = "720p HD";
                break;
        }
    }
    /**
     * Zwraca dane o subskrypcji sformatowane jako string.
     * @return String zawierający dane o subskrypcji.
     */
    public String getString()
    {
        String outp = new String();
        
        outp += "Typ: " + type +
                "\nMiesięczna cena: " + Double.toString(price) +
                "\nUrządzenia: " + Integer.toString(devices) +
                "\nRozdzielczość: " + resolution;
        
        return outp;
    }
    /**
     * Zwraca typ subskrypcji
     * @return Typ subskrypcji
     */
    public String getType()
    {
        return this.type;
    }
    
    /**
     * Zwraca bazową cenę subskrypcji typu Basic.
     * @return Bazowa cena subskrypcji Basic.
     */
    public static double getBasicPrice() {
        return basicPrice;
    }
    
    /**
     * Ustawia nową cenę dla wszystkich nowotworzonych
     * subskrypcji typu Basic w systemie.
     * @param basicPrice - nowa cena.
     */
    public static void setBasicPrice(double basicPrice) {
        Subscription.basicPrice = basicPrice;
    }
    
    /**
     * Zwraca bazową cenę subskrypcji typu Family.
     * @return Bazowa cena subskrypcji Family.
     */
    public static double getFamilyPrice() {
        return familyPrice;
    }
    
    /**
     * Ustawia nową cenę dla wszystkich nowotworzonych
     * subskrypcji typu Family w systemie.
     * @param familyPrice - nowa cena.
     */
    public static void setFamilyPrice(double familyPrice) {
        Subscription.familyPrice = familyPrice;
    }
    
    /**
     * Zwraca bazową cenę subskrypcji typu Premium.
     * @return Bazowa cena subskrypcji Premium.
     */
    public static double getPremiumPrice() {
        return premiumPrice;
    }
    
    /**
     * Ustawia nową cenę dla wszystkich nowotworzonych
     * subskrypcji typu Family w systemie.
     * @param premiumPrice - nowa cena.
     */
    public static void setPremiumPrice(double premiumPrice) {
        Subscription.premiumPrice = premiumPrice;
    }
    
    /**
     * Zwraca wartość konkretnego obiektu subskrypcji.
     * @return Wartość konkretnej subskrypcji klienta.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Ustawia cenę dla tego konkretnego obiektu subskrypcji.
     * @param price Nowa cena.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
