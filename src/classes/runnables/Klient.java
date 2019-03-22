package classes.runnables;

import classes.entities.*;
import classes.misc.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

/**
 * Klasa symulująca użytkownika systemu - pole posiada unikalne id,
 * generowany losowo numer karty, adres e - mail, datę urodzenia oraz rodzaj
 * preferowanej subskrypcji.
 */
public class Klient implements Byt
{
    private static int nextId = 10000;
    private final int id;
    private final int nrKarty;
    private LinkedList<WpisBiblioteczny> listaWpisow;
    
    private final Platforma platform;
    private final String eMail;
    private final Date dataUrodzenia;
    private Subscription sub;
    
    /**
     * Konstruktor, losuje wszystkim polom losowe wartości i
     * przypisuje klientowi darmową subskrypcję.
     * @param px Platforma, której klientem jest dany Klient.
     */
    public Klient(Platforma px)
    {
        Random x = new Random();
        this.id = Klient.nextId;
        Klient.nextId++;
        this.platform = px;
        this.eMail = Utilities.genRandomString(x.nextInt(8)+5) + "@" +
                Utilities.genRandomString(x.nextInt(5)+5).toLowerCase() + "." +
                Utilities.genRandomString(2).toLowerCase();
        
        this.nrKarty = x.nextInt(90000)+10000;
        this.dataUrodzenia = Utilities.generateTime(1972, 2005).getTime();
        this.sub = new Subscription(0);
        this.listaWpisow = new LinkedList<>();
    }
    /**
     * Funkcja wybierająca nową, losową subskrypcję (abonament)
     */
    public void buyRndSubscription()
    {
        int x = new Random().nextInt(3) + 1;
        sub = new Subscription(x);
    }
    
    /**
     * Funkcja, która powoduje "rezygnację klienta z abonamentu i powrót
     * do wersji `free` "
     */
    public void undoSubscription()
    {
        sub = new Subscription(0);
    }
    
    /**
     * Łączy pola instancji w tekst tak, aby można było je przedstawić
     * na ekranie (bardziej opisowo - funkcja ToString zwróci jedynie
     * krótką reprezentację na potrzeby jLista)
     * @return outp - String, będący gotowym opisem produktu.
     */
    public String getClientAsString()
    {
        
        String outp = new String();
        outp += "ID: " + Integer.toString(id) +
                "\neMail: " + eMail +
                "\nNumer karty: " + Integer.toString(nrKarty) +
                "\nData urodzenia: " + new SimpleDateFormat("dd.MM.yyyy").format(dataUrodzenia) +
                "\nSubskrypcja: \n" + sub.getString();
                
        return outp;
    }
    /**
     * Funkcja powodująca kupienie danego produktu przez
     * danego klienta i  albo obejrzenie go (Serial - jeden odcinek i Stream)
     * albo dodanie wpisu bibliotecznego.
     * @param x - Produkt będący przedmiotem transakcji.
     */
    public void kupProdukt(Product x)
    {   
        
        // Jeśli klient nie ma subskrypcji bądź produkt jest streamem, których
        // abonament nie obejmuje
        if (this.sub.getType().equals("None") || x instanceof Stream);
        {   
            double naleznosc = x.getCena();
            
            //to oblicz należność, uwzględniając promocję jeśli jakaś jest
            //i dany produkt nie jest serialem, który nie jest objęty promocją
            
            if (platform.getPromo() != null && !(x instanceof Serial))
                naleznosc = naleznosc * (platform.getPromo().getObnizka() / 100.0f) ;
            
            //i dopchaj się do platformy i zwiększ jej saldo o cenę,
            //jaką zapłacił klient za kupno produktu.
            platform.setSaldo(platform.getSaldo() + naleznosc);
            
            
        }   
        //jeśli obiekt jest filmem to dodaj wpis biblioteczny;
        if (x instanceof Film)
        {
            WpisBiblioteczny wpis = new WpisBiblioteczny(x.getId(), ((Film) x).getCzasDostepu());
            listaWpisow.add(wpis);
        }
            
        // jeśli obiekt jest serialem to wylosuj losowy odcinek i go obejrzyj
        // oraz zapłać dystrybutorowi należną kwotę
            
        else if (x instanceof Serial)
        {
            Odcinek episode = ((Serial)x).getRandomEp();
            episode.setObejrzeniaDzis(episode.getObejrzeniaDzis() + 1);
            x.setObejrzenia(x.getObejrzenia() + 1);
            
           
            if (x.getDystr().getUmowa().isRyczalt() == false)
            {
               double naleznosc = x.getDystr().getUmowa().getWartosc();
               x.getDystr().setSaldo(x.getDystr().getSaldo() + naleznosc);
               this.platform.setSaldo(this.platform.getSaldo() - naleznosc);
            }
        }
           
        else if (x instanceof Stream)
        {
            x.setObejrzenia(x.getObejrzenia() + 1);
            if (x.getDystr().getUmowa().isRyczalt() == false)
            {
               double naleznosc = x.getDystr().getUmowa().getWartosc();
               x.getDystr().setSaldo(x.getDystr().getSaldo() + naleznosc);
               this.platform.setSaldo(this.platform.getSaldo() - naleznosc);
            }
        }
    }
    
    
    /**
     * Funkcja na potrzeby wątku - losuje akcję, którą wykona klient.
     * @param sim - Symulacja, w ramach której działa klient
     */
    @Override
    public void performAction(Symulacja sim) 
    {
        Random rnd = new Random();
        
        /* Wybór akcji:
         * 0: brak akcji
         * 1: kup coś
         * 2: obejrzyj coś z listy bibliotecznej
         * 3: kup/anuluj subskrypcję
         */
        
        int akcja = rnd.nextInt(3);
        switch(akcja)
        {
            case 1:        // wylosuj losowy produkt spośród dostępnych i go kup
                           // jeżeli jakieś są dostępne
                if (sim.getListaProduktow().size() > 0)
                {
                    Product prod = sim.getListaProduktow().get(rnd.nextInt(
                                   sim.getListaProduktow().size()));
                    this.kupProdukt(prod);
                }
            case 2:
                
                if (!(this.listaWpisow.isEmpty()))
                {
                    int index = rnd.nextInt(this.listaWpisow.size());
                    int id = this.listaWpisow.get(index).getId();
                    
                    Product p = null;
                    //Wybierz produkt o wylosowanym ID
                    for (Product x : sim.getListaProduktow())
                    {
                        if (x.getId() == id)
                        {
                            p = x;
                            break;
                        }
                    }
                    
                    if (p != null)
                    {
                        p.setObejrzenia(p.getObejrzenia() + 1);
                        if (p.getDystr().getUmowa().isRyczalt() == false)
                        {
                            double naleznosc = p.getDystr().getUmowa().getWartosc();
                            p.getDystr().setSaldo(p.getDystr().getSaldo() + naleznosc);
                            this.platform.setSaldo(this.platform.getSaldo() - naleznosc);
                        }
                    }    
                }
                                
            case 3:        
                
                //Kup losową subskrypcję, bądź, jeśli jakąś masz, 
                //zrezygnuj z niej z prawdopodobieństwem 1/4  
                
                if (this.sub.getType().equals("None"))
                {
                    this.buyRndSubscription();
                }
                else
                {
                    if (rnd.nextInt(4) == 0)
                        this.undoSubscription();
                }
                
        }
    }

    @Override
    public String toString() {
        return "Klient " + Integer.toString(id);
    }   
    /**
     * Funkcja wywoływana w symulacji z odstępami jednodniowymi, obniża 
     * pozostały czas, w którym produkt o danym ID jest dostępny dla
     * danego klienta o jeden dzień. W przypadku, gdy czas zejdzie poniżej
     * zera, wpis zostaje całkowicie usunięty z biblioteki.
     */
    public void testAndSet()
    {
        LinkedList<WpisBiblioteczny> doUsuniecia = new LinkedList();
        for (WpisBiblioteczny i : this.listaWpisow)
        {
            i.setPozostaleDni(i.getPozostaleDni() - 1);
            if (i.getPozostaleDni() <= 0)
                doUsuniecia.add(i);
        }
        
        this.listaWpisow.removeAll(doUsuniecia);
    }
    
    /**
     * Getter zwracający obiekt subskrypcji dla danego wątku.
     * @return Subskrypcja przypisana do danego klienta.
     */
    public Subscription getSub() {
        return sub;
    }

    @Override
    public String returnInfo() {
        String info = new String();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        info += "ID klienta: " + Integer.toString(id);
        info += "\nUrodzony: " + format.format(dataUrodzenia);
        info += "\nE-mail: " + eMail;
        info += "\nNumer karty: " + Integer.toString(nrKarty);
        info += "\nTyp subskrypcji: " + sub.getType();
        
        return info;
    }
}
