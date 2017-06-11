/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posrednik;

import entiteti.Korisnik;
import entiteti.Apartman;
import entiteti.Apartmanisobe;
import entiteti.Korisnik;
import entiteti.Kupac;
import entiteti.Kupac;
import entiteti.Prodavac;
import entiteti.Prodavac;
import entiteti.Rezervacija;
import entiteti.Soba;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Queue;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author gdin Ješić
 */
public class Main {
    @Resource(lookup = "MyQueue")
    static Queue queue;
    @Resource(lookup = "MyTopic")
    static Topic topic;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    
    
    private static EntityManagerFactory emf=null;
    private static EntityManager em=null;
    private static boolean kreiranoPocetno=false;
    
    
    public static void main(String[] args) {
        emf= Persistence.createEntityManagerFactory("PosrednikPU");
        em = emf.createEntityManager();
        if(!kreiranoPocetno){
            kreirajPocetnoStanje(em);
            kreiranoPocetno=true;
        }
        Server server=new Server();
        server.start();
    }
    public static void kreirajPocetnoStanje(EntityManager em){
        em.getTransaction().begin();
        List<Kupac> kupci=new ArrayList<>();
        kupci.add(new Kupac("griza","griza","Griza","Grizic","066/8701871","griza@gmail.com","215363161371"));
        kupci.add(new Kupac("zlatimir","zlatimir","Zlatimir","Zlatic","066/8701871","zlatimir@gmail.com","634563161371"));
        kupci.add(new Kupac("darinka","darinka","Darinka","Darinkic","066/8701871","darinka@gmail.com","785553161371"));
        kupci.add(new Kupac("katica","katica","Katica","Katic","066/8701871","katica@gmail.com","990363161371"));
        kupci.add(new Kupac("miraleb","miraleb","Miraleb","Miralebovic","066/8701871","miraleb@gmail.com","275433161371"));
        kupci.add(new Kupac("vujko","vujko","Vujko","Vujkovic","066/8701871","vujko@gmail.com","115363161371"));
        kupci.add(new Kupac("zivka","zivka","Zivka","Zivkic","066/8701871","zivka@gmail.com","032563161371"));
        kupci.add(new Kupac("mitar","mitar","Mitar","Mitric","066/8701871","mitar@gmail.com","766663161371"));
        kupci.add(new Kupac("evnoje","evnoje","Evnoje","Evnojic","066/8701871","evnoje@gmail.com","233363161371"));
      
        List<Prodavac> prodavci=new ArrayList<>();
        prodavci.add(new Prodavac("krsto","krsto","Krsto","Krstajic","066/8701871","krsto@gmail.com",33));
        prodavci.add(new Prodavac("mita","mita","Mita","Mitic","066/8701871","mita@gmail.com",34));
        prodavci.add(new Prodavac("milojica","milojica","Milojica","Milojevic","066/8701871","milojica@gmail.com",35));
        
        List<Apartman> apartmani=new ArrayList<>();
        apartmani.add(new Apartman("dvosoban", "Srbija", "Bograd", "Ustanicka", 33, "Jako lep, NOV NOV!"));  
        apartmani.add(new Apartman("trosoban", "Hrvatska", "Zagreb", "Zagrebacka Arena", 47, "Lip i jeftin.. par tisuca."));
        apartmani.add(new Apartman("jednosoban", "Srbija", "Kragujevac", "Kragujevacka", 155, "Dodji i uzmi"));
        apartmani.add(new Apartman("jednosoban", "Srbija", "Novi Sad", "Vojvodjanska", 24, ""));
        
        List<Soba> sobe=new ArrayList();
        sobe.add(new Soba(3, "lepa soba za par sa decom."));
        sobe.add(new Soba(2, "romanticna soba"));
        sobe.add(new Soba(1, "soba za studenta"));
        sobe.add(new Soba(4,"soba za celu familiju"));
        sobe.add(new Soba(1,"soba za jednu osobu sa kerom."));
        sobe.add(new Soba(2,"soba za dva sabana."));
        
        List<Apartmanisobe> apartmanisobe=new ArrayList<>();
        apartmanisobe.add(new Apartmanisobe(apartmani.get(0),sobe.get(0)));
        apartmanisobe.add(new Apartmanisobe(apartmani.get(0),sobe.get(1)));
        apartmanisobe.add(new Apartmanisobe(apartmani.get(0),sobe.get(2)));
        apartmanisobe.add(new Apartmanisobe(apartmani.get(1),sobe.get(3)));
        apartmanisobe.add(new Apartmanisobe(apartmani.get(1),sobe.get(4)));
        apartmanisobe.add(new Apartmanisobe(apartmani.get(2),sobe.get(5)));
        
        for (Kupac ku : kupci) {
            em.persist(ku);
        }
        
        for (Prodavac pr : prodavci) {
            em.persist(pr);
        }
        
        for(Apartman ap:apartmani){
            em.persist(ap);
        }
        for(Soba so:sobe){
            em.persist(so);
        }
        for(Apartmanisobe apso:apartmanisobe){
            em.persist(apso);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
        }
        em.getTransaction().commit();
        System.out.println("***************Baza inicijalizovana!!!**************");
    }
    public static Apartman dodajApartman(String naziv, String drzava, String grad, String ulica, Integer broj, String description){
        em.getTransaction().begin();
        Apartman apartman=new Apartman(naziv,drzava,grad,ulica,broj,description);
        em.persist(apartman);
        em.getTransaction().commit();
        System.out.println("Registrovan je novi "+apartman.getName()+" apartman u "+apartman.getState()+", grad "+apartman.getCity()+" i ulica: "+apartman.getStreet());
        return apartman;
    }
    public static void izmeniApartman(int id,String opis){
        Query query = em.createQuery("UPDATE Apartman a SET a.description = '"+opis+"' WHERE a.id = "+ id);
        query.executeUpdate();
        em.getTransaction().commit();
        System.out.println("Apartman sa ID: "+id+" je promenio opis u: '"+opis+"'");
    }
    public static void obrisiApartman(int id){
        Query query=em.createQuery("DELETE FROM Apartman a WHERE a.id = "+id);
        query.executeUpdate();
        em.getTransaction().commit();
        System.out.println("Obrisan je apartman sa id: "+id);
    }
    public static void dodajSobuUapartman(Apartman a,Integer brojOsoba,String opis){
        em.getTransaction().begin();
        Soba soba=new Soba(brojOsoba,opis);
        em.persist(soba);
        Apartmanisobe apartmaniSobe=new Apartmanisobe(a,soba);
        em.persist(apartmaniSobe);
        em.getTransaction().commit();
        System.out.println("U "+a.getName()+" apartman je dodata soba za "+soba.getBrosoba()+ " osoba/e.");
    }
    public static void izmeniSobu(int id,String opis){
        Query query = em.createQuery("UPDATE Soba s SET s.description = '"+opis+"' WHERE s.id = "+ id);
        query.executeUpdate();
        em.getTransaction().commit();
        System.out.println("Soba sa ID: "+id+" je promenila opis u: '"+opis+"'");
    }
    public static void obrisiSobu(int id){
        Query query=em.createQuery("DELETE FROM Soba s WHERE s.id = "+id);
        query.executeUpdate();
        em.getTransaction().commit();
        System.out.println("Obrisana je soba sa id: "+id);
    }
    public static void izmeniKorisnika(Korisnik k){
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Korisnik k SET k.name = '"+k.getName()+"' WHERE k.username = '"+k.getUsername()+"'");
        query.executeUpdate();
        em.getTransaction().commit();
        System.out.println("Korisnik: "+k.getUsername()+" je promenio ime u: "+k.getName());
    }
    public static List<Apartmanisobe> dohvatiSobeUapartmanima(){
        List<Apartmanisobe> sobe;
        Query query = em.createQuery("Select a from Apartmanisobe a");
        sobe = query.getResultList();
        System.out.println("Dohvacena je lista svih soba po apartmanima");
        return sobe;
    }
    public static void napraviRezervaciju(Rezervacija r){
        
        em.getTransaction().begin();
        Rezervacija r1=new Rezervacija(r.getIdapartmanisobe(),r.getDatumprijave(),r.getDatumodjave());
        em.persist(r1);
        em.getTransaction().commit();
        System.out.println("Napravljena je nova rezervacija sobe u apartmanu ciji je ID: "+r1.getId()+"za datum od: "+r1.getDatumprijave()+" do: "+r1.getDatumodjave());
        
    }
    
}
