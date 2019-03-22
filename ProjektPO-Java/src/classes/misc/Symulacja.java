package classes.misc;

import classes.runnables.*;
import classes.entities.*;
import java.util.*;

/**
 *
 * @author Mateusz Ksok
 */
public class Symulacja extends Thread
{
    private final Calendar obecnaData;
    private final Platforma flixnet;
    
    private volatile LinkedList<Product> listaProduktow;
    private volatile LinkedList<ExtThread> listaDystrybutorow;
    private volatile LinkedList<ExtThread> listaKlientow;
    private volatile boolean paused;
    private volatile boolean finish;
    /**
     * Konstruktor symulacji inicjalizujący jej wszystkie
     * pola.
     */
    public Symulacja() 
    {
        finish = false;
        paused = false;
        obecnaData = Calendar.getInstance();
        flixnet = new Platforma();
        listaDystrybutorow = new LinkedList();
        listaProduktow = new LinkedList();
        listaKlientow = new LinkedList();
    }
    
    @Override
    public void run()                           //ZRÓB PORZĄDNĄ SYNCHRONIZACJĘ
    {
        while(!finish)
        {
            if (!paused)
            {   
                // Na początek miesiąca zapłać wszystkim dystrybutorom, z którymi
                // masz umowy na ryczałt 
                if (this.obecnaData.get(Calendar.DAY_OF_MONTH) == 1)
                {
                    for (Umowa u : flixnet.getListaUmow())
                    {
                        if (u.isRyczalt())
                        {
                            double naleznosc = u.getWartosc();
                            u.getDystryb().setSaldo(u.getDystryb().getSaldo() + naleznosc);
                            flixnet.setSaldo(flixnet.getSaldo() - naleznosc);
                        }
                    }
                    
                    //Następnie pobierz od klientów należność za abonament
                    for (ExtThread klient : listaKlientow)
                    {
                        double naleznosc = ((Klient)klient.getByt()).getSub().getPrice();
                        flixnet.setSaldo(flixnet.getSaldo() + naleznosc);
                    }
                    
                    if (this.flixnet.sprawdzCzyStrata() >= 3)
                        finish = true;
                }
                
                LinkedList<Product> toDelete = new LinkedList();
                
                for (Product p : listaProduktow)
                {
                    if (p instanceof Stream)
                    {
                        if (((Stream) p).getDataWyswietlenia().before(this.obecnaData.getTime()));
                            toDelete.add(p);
                    }
                }
                
                listaProduktow.removeAll(toDelete);
                
                // obniżenie i usunięcie wpisów bibliotecznych dla klientów
                for (ExtThread klient : listaKlientow)
                {
                    ((Klient)klient.getByt()).testAndSet();
                }
 
                this.performAction();
                
                //obudz wszystkie wątki
                for (ExtThread i : listaDystrybutorow)
                {
                    i.setAkcja(true);
                }
                for (ExtThread k : listaKlientow)
                {
                    k.setAkcja(true);
                }
                    // sleep nie do synchronizacji, lecz do uzyskania 1.5 - sekundowych
                    // odstępów pomiędzy zmianami daty.
                    
                try {
                    Thread.sleep(1500); 
                } catch (InterruptedException ex) {
                    System.out.println("Thread interrupted." + ex.getMessage());
                }
                
                // jeśli jest promocja - zmniejszenie jej czasu trwania i usunięcie
                // jej jeśli czas trwania zejdzie do zera
                if (flixnet.getPromo() != null)
                {
                    flixnet.getPromo().setCzasTrwania(flixnet.getPromo().getCzasTrwania() - 1);
                    if (flixnet.getPromo().getCzasTrwania() <= 0)
                        flixnet.setPromo(null);
                }
                //Dodawanie 1 do daty.
                obecnaData.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
    }
    
    /**
     * Metoda symulująca wykonanie akcji. Losowana jest liczba od 0 do 3, 
     * każda oznacza jakąś akcję (bądź 0 - jej brak).
     */
    public void performAction()
    {
        // 1: nowa promocja
        // 2: nowy klient
        // 3: nowy produkt
        Random rnd = new Random();
        int whatToDo = rnd.nextInt(4);
        int chance = rnd.nextInt(5);
        
        switch(whatToDo)
        {
            case 1:
                if (this.flixnet.getPromo() == null && chance < 2)
                    this.flixnet.setPromo(new Promocja());
            case 2:
                if (!this.listaProduktow.isEmpty() && chance < 1)
                    this.newKlient();
            case 3:
                
                if (chance < 1)
                    this.newProduct();
        }   
        
    }
      
    
    /**
     * Funkcja tworząca nowy produkt w symulacji, 
     * @return true jeśli się powiodło, false jeśli nie.
     */
    public boolean newProduct()
    {        
        if (!listaDystrybutorow.isEmpty())    //jeśli lista dystrybutorów nie jest pusta
        {
            Dystrybutor db = (Dystrybutor)listaDystrybutorow.get
            (new Random().nextInt(listaDystrybutorow.size())).getByt();
            
            listaProduktow.add(db.wypuscProdukt());
            
            return true;
        }
        else return false;
    }
    
    /**
     * Funkcja tworząca nowy wątek dystrybutora
     * i startująca go.
     */
    public void newDystrybutor()
    {
        Dystrybutor db = new Dystrybutor(flixnet);
        ExtThread th = new ExtThread(db, this);
        listaDystrybutorow.add(th);
        th.start();
        flixnet.getListaUmow().add(db.getUmowa());
    }
    
    /**
     * Funkcja tworząca nowy wątek klienta
     * i startująca go.
     */
    public void newKlient()
    {
        Klient kl = new Klient(flixnet);
        ExtThread th = new ExtThread(kl, this);
        this.getListaKlientow().add(th);
        th.start();
    }
    
    /**
     * Funkcja zwracająca obecną datę w symulacji.
     * @return Obecna data.
     */
    public Calendar getObecnaData() {
        return obecnaData;
    }
    
    /**
     * Funkcja zwracająca flagę mówiącą o tym, czy dany
     * wątek symulacji jest pauzowany czy nie.
     * @return true - jeśli spauzowany, w przeciwnym wypadku - false.
     */
    public synchronized boolean isPaused() {
        return paused;
    }

    /**
     * Funkcja zwracająca flagę mówiącą o tym, czy dany
     * wątek symulacji się skończył czy nie.
     * @return true - jeśli się skończył, w przeciwnym wypadku - false.
     */
    public synchronized boolean isFinish() {
        return finish;
    }
    
    /**
     * Metoda służąca do pauzowania i kontynuowania symulacji.
     * @param paused true - kontynuacja symulacji, false - pauzowanie symulacji.
     */
    public synchronized void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    /**
     * Funkcja służąca do kończenia symulacji - setFinish
     * @param finish - True - jeśli chcemy skończyć symulację, w 
     * przeciwnym wypadku - false.
     */
    public synchronized void setFinish(boolean finish) {
        this.finish = finish;
    }
    
    /**
     * Funkcja zwracająca listę produktów w symulacji.
     * @return Lista produktów w systemie.
     */
    public synchronized LinkedList<Product> getListaProduktow() 
    {
        return listaProduktow;
    }
    
    /**
     * Funkcja zwracająca platformę podpiętą do danej symulacji.
     * @return Platforma podpięta pod system.
     */
    public Platforma getFlixnet() {
        return flixnet;
    }
    
    /**
     * Metoda zwracająca listę dystrybutorów w systemie.
     * @return Lista dystrybutorów w symulacji.
     */
    public synchronized LinkedList<ExtThread> getListaDystrybutorow() {
        return listaDystrybutorow;
    }
    
    /**
     * Metoda zwracająca klientów będących w systemie.
     * @return Lista klientów w symulacji.
     */
    public synchronized LinkedList<ExtThread> getListaKlientow() {
        return listaKlientow;
    }
}