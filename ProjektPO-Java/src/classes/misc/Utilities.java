package classes.misc;
import classes.entities.Odcinek;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author Kiosku
 */
public class Utilities 
{   
    /**
     * Funkcja generująca losowy ciąg znaku o podanej długości.
     * @param len Długość wyjściowego Stringa
     * @return Losowy ciąg znaków typu string
     */
    public static String genRandomString(int len)
    {
        String charList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String output = new String();
        Random x = new Random();
        for (int i = 0; i < len; i++)
        {
            int index = x.nextInt(charList.length());
            output = output + charList.charAt(index);
        }
        return output;
    }
    
    /**
     * Funkcja służąca do wygenerowania linku do filmu.
     * 
     * @return String stylizowany na link do filmu w 
     * serwisie YouTube.
     */
    public static String generateLink()
    {
        String tail = "=" + Utilities.genRandomString(new Random().nextInt(3) + 9);
        String link = "https://www.youtube.com/watch?v" + tail;
        return link;
    }
    /**
     * Funkcja służąca do losowego generowania tytułu filmu/serialu
     * spośród różnych kombinacji elementów w czterech tablicach.
     * @return String mogący posłużyć jako tytuł filmu/serialu.
     */
    public static String generateName()
    {
        String[] tab0 =
        {
            "Asterix i Obelix: ",
            "Harry Potter i ",
            "Gwiezdne Wojny: ",
            "Szklana Pułapka: ",
            "Kapitan Ameryka: ",
            "Avengers: ",
            "Resident Evil: ",
            "Fallout: ",
            "Shrek: ",
            "Hobbit: ",
            "Władca Pierścieni: ",
            "Iron Man: ",
            "Koziołek Matołek i "
        };
        
        String[] tab1 = 
        {
            "Piekło ",
            "Atak ",
            "Gang ",
            "Zemsta ",
            "Władca ",
            "Zagłada ",
            "Pogromca ",
            "Koszmar ",
            "Agentura ",
            "Misja ",
            "Prawdziwa historia ",
            "Historia ",
            "Koniec ",
            "Liga ",
            "Nadzieja "
        };
                
        String[] tab2 =
        {
            "Dzikich ",
            "Różowych ",
            "Morderczych ",
            "Wielkich ",
            "Ogromnych ",
            "Laserowych ",
            "Małych ",
            "Głodnych ",
            "Niepokonanych ",
            "Tajnych ",
            "Niesprawiedliwych "
        };
        
        String[] tab3 =
        {
            "Klonów",
            "Bohaterów",
            "Kuców",
            "Komandosów",
            "Jaszczurów",
            "Strażników Galaktyki",
            "Szpiegów",
            "Snajperów",
            "Weteranów",
            "Żołnierzy",
            "Zombie"
        };
        Random rnd = new Random();
        int i0 = rnd.nextInt(tab0.length);
        int i1 = rnd.nextInt(tab1.length);
        int i2 = rnd.nextInt(tab2.length);
        int i3 = rnd.nextInt(tab3.length);
        int i4 = rnd.nextInt(2);
        
        String str = new String();
        if (i4 == 0) str = tab0[i0] + tab1[i1] + tab2[i2] + tab3[i3];
        else str = tab1[i1] + tab2[i2] + tab3[i3];  
        return str;
    }
    /**
     * @param title - tytuł produktu
     * @param className - rodzaj produktu
     * @param data - data produkcji
     * @param ocena - średnia ocena produktu
     * @param kraje - lista (ArrayList) krajów produkcji
     * 
     * Funkcja przyjmująca powyższe parametry i generująca z nich opis dla
     * danego produktu. Żadne z powyższych pól nie jest zmieniane w trakcie
     * działania funkcji.
     * 
     * @return String, który sformatowany został na wzór opisu filmu.
     * na wzór 
     */
    public static String generateDesc(String title, 
                                      String className,
                                      Date data,
                                      double ocena,
                                      ArrayList<Kraj> kraje)
    {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String desc = title + " to " + className + "\n wyprodukowany " +
                formatter.format(data) + ". \nŚrednia ocena: " 
                + Double.toString(ocena) +"\nKraje produkcji: \n";
        
        for(Kraj e: kraje)
        {
            desc = desc + "- " + e.toString()+"\n";
        }
        
        return desc;
    }
    
    /**
     * Funkcja generująca liczbę z zakresu od 1 do 10 (włącznie)
     * z prawdopodobieństwami:
     * 3/4 - 6-8
     * 1/8 - 1-2
     * 1/8 - 8-10
     * @return Liczba typu double z zakresu 1-10 (łącznie) z precyzją 
     * do dwóch miejsc po przecinku mogąca reprezentować 
     * ocenę produktu w danej skali.
     */
    
    public static Double generateRating()
    {
        Random rand = new Random();
        double rating;
        int chance = rand.nextInt(9);
        if (chance <= 6)
        {
            rating = rand.nextInt(201)/100.0f + 6;
        }
        else if (chance == 7)
        {
            rating = rand.nextInt(401)/100.0f + 1;
        }
        else rating = rand.nextInt(201)/100.0f + 8;
        
        rating = Math.round(rating * 100.0)/100.0;
        return rating;
    }
    
    /**
     * @param minYear dolna granica zakresu (przedział domknięty)
     * @param maxYear górna granica zakresu (przedział otwarty)
     * Funkcja generująca losową datę z danego przedziału lat.
     * @return Zmienna typu Calendar z wygenerowaną datą.
     */
    
    public static Calendar generateTime(int minYear, int maxYear)
    {
        Random rand = new Random();
        int x = rand.nextInt(maxYear - minYear) + minYear;
        int y = rand.nextInt(12);
        int z = rand.nextInt(32);
        
        Calendar cal = Calendar.getInstance();
        cal.set(x,y,z);
        return cal;
    }
    
    /**
     * Funkcja generująca listę 1-3 (losowo) krajów spośród wszystkich
     * wartości dostępnych w enumie "Kraj".
     * @return ArrayList(Kraj) z jedną bądź kilkoma propozycjami mogącymi
     * symulować kraje produkcji.
     */
    public static ArrayList<Kraj> generateCountries()
    {
        int amount = (new Random()).nextInt(2) + 1;
        HashSet<Kraj> temp = new HashSet();
        for (int i = 0; i<amount; i++)
        {
            temp.add(Kraj.getRandomCountry());
        }
        ArrayList<Kraj> lista = new ArrayList<>(temp);
        return lista;
    }
    /**
     * Funkcja generująca 2-4 nazwiska aktorów złączone losowo
     * spośród bazy imion i nazwisk.
     * @return ArrayList(String) z 2-4 propozycjami symulującymi
     * nazwiska aktorów.
     */
    public static ArrayList<String> generateActors()
    {
        String[] names = 
        {
            "Danny ",
            "Chris ",
            "Robert ",
            "Nicolas ",
            "Sylvester ",
            "Bruce ",
            "Saul ",
            "Tommy ",
            "Donald ",
            "Chuck ",
            "Angelina ",
            "Penelope ",
            "Salma ",
            "Emma ",
            "Marlon ",
            "Marion ",
            "Leonardo ",
            "Tony ",
            "Scarlett ",
            "Eva ",
            "Jack ",
            "Brad ",
            "Helena ",
            "Julia ",
            "Kevin "
        };
        
        String[] surnms =
        {
            "Trejo",
            "Pratt",
            "de Niro",
            "Downey Jr.",
            "Cage",
            "Stallone",
            "Willis",
            "Lee",
            "Hudson",
            "Wiseau",
            "Trump",
            "Glover",
            "Norris",
            "Jolie",
            "Cruz",
            "Hayek",
            "Stone",
            "Brando",
            "Cotillard",
            "Di Caprio",
            "Da Vinci",
            "Stark",
            "Johansson",
            "Green",
            "Nicholson",
            "Black",
            "Pitt",
            "Bonham Carter",
            "Roberts",
            "Spacey"
        };

        HashSet<String> actors = new HashSet();
        Random rnd = new Random();
        int amount = (rnd.nextInt(3) + 2);
        
        for (int i = 0; i < amount; i++)
        {
            String temp = names[rnd.nextInt(names.length)] + 
                    surnms[rnd.nextInt(surnms.length)];
            
            actors.add(temp);
        }
        ArrayList<String> list = new ArrayList<>(actors);
        return list;
    }
    /**
     * Funkcja generująca listę list(sezonów) obiektów typu Odcinek.
     * Serial rozpoczą między 1978 i 2017 rokiem, odcinki wychodzą co 2 dni,
     * Emisja każdego sezonu rozpoczyna się 
     * 3 miesiące po zakończeniu poprzedniego.
     * @return  Kolekcja typu ArrayList(ArrayList(Odcinek)), która 
     * symuluje odcinki konkretnego serialu.
     */
    public static ArrayList<ArrayList<Odcinek>> generateSeasons()
    {
        Calendar cal = Utilities.generateTime(1978, 2018);
        Random rnd = new Random();
        int seasons = rnd.nextInt(6) + 2;
        ArrayList<ArrayList<Odcinek>> sezony = new ArrayList();
        for(int i = 0; i < seasons; i++)
        {
            ArrayList<Odcinek> temp = new ArrayList();
            int eps = rnd.nextInt(7) + 7;
            for (int j = 0; j < eps; j++)
            {
                Odcinek x = new Odcinek(cal);
                temp.add(x);
                cal.add(Calendar.DATE, 2);
            }
            
            sezony.add(temp);
            cal.add(Calendar.MONTH, 3);
        }
        return sezony;
    }
}
