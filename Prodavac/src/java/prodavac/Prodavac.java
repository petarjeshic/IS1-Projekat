/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodavac;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import entiteti.Apartman;
import entiteti.Korisnik;
import entiteti.Soba;
/**
 *
 * @author gdin Ješić
 */
public class Prodavac extends Thread{
    int id;
    String username;
    public Prodavac(int id,String username){
        this.id=id;
        this.username=username;
    }
    @Override
    public void run(){
        System.out.println("Startovan je prodavac broj: "+id);
        JMSContext context = Main.connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        while(true){
            try {
                int idxImena = new Random().nextInt(Main.imena.length);
                int idxStanovi = new Random().nextInt(Main.stanovi.length);
                String ime=Main.imena[idxImena];
                String naziv=Main.stanovi[idxStanovi];
                int sekunde = (int)(Math.random() * 6 + 10);
                //dodavanje APARTMANA
                String napravi="dodajApartman";
                ObjectMessage objectMessage = context.createObjectMessage();
                objectMessage.setStringProperty("nazivOperacije", napravi);
                Apartman apartman=new Apartman(naziv, "Srbija", "Beograd", "Balkanska", 31, "Nova gradnja.");
                objectMessage.setObject(apartman);
                producer.send(Main.queue, objectMessage);
                System.out.println("Poslat je zahtev da se ubaci u bazu "+naziv+" stan.");
                Thread.sleep(sekunde * 1000);
                //menjanje KORISNIKA
                ObjectMessage objectMessage1=context.createObjectMessage();
                objectMessage1.setStringProperty("nazivOperacije", "izmeniKorisnika");
                Korisnik k=new Korisnik(username, "nesto", Main.imena[idxImena], "nesto", "nesto", "nesto");
                objectMessage1.setObject(k);
                producer.send(Main.queue,objectMessage1);
                System.out.println("Poslat je zahtev da se izmeni ime prodavcu ciji je ID: "+this.id);
                Thread.sleep(sekunde * 1000);
                //izmena APARTMANA
                objectMessage.clearBody();
                objectMessage.clearProperties();
                objectMessage.setStringProperty("nazivOperacije", "izmeniApartman");
                apartman.setDescription("izmenjen opis.");
                objectMessage.setObject(apartman);
                producer.send(Main.queue, objectMessage);
                System.out.println("Poslat je zahtev da se izmeni apartman "+naziv+".");
                Thread.sleep(sekunde * 1000);
                //dodavanje SOBE
                objectMessage.clearBody();
                objectMessage.clearProperties();
                objectMessage.setStringProperty("nazivOperacije","dodajSobuUapartman");
                Soba soba=new Soba(3, "opis sobe.");
                objectMessage.setObject(apartman);
                objectMessage.setObject(soba);
                producer.send(Main.queue, objectMessage);
                System.out.println("Poslat je zahtev da se doda soba za "+soba.getBrosoba()+" osobe.");
                Thread.sleep(sekunde * 1000);
                //izmena SOBE
                objectMessage.clearBody();
                objectMessage.clearProperties();
                objectMessage.setStringProperty("nazivOperacije", "izmeniSobu");
                soba.setOpis("izmenjen opis sobe.");
                objectMessage.setObject(soba);
                producer.send(Main.queue, objectMessage);
                System.out.println("Poslat je zahtev da se izmeni soba za "+soba.getBrosoba()+" osobe.");
                Thread.sleep(sekunde * 1000);
                //brisanje SOBE
                objectMessage.clearBody();
                objectMessage.clearProperties();
                objectMessage.setStringProperty("nazivOperacije", "obrisiSobu");
                objectMessage.setObject(soba);
                producer.send(Main.queue, objectMessage);
                System.out.println("Poslat je zahtev da se obrise soba za "+soba.getBrosoba()+" osobe.");
                Thread.sleep(sekunde * 1000);
                //brisanje APARTMANA
                objectMessage.clearBody();
                objectMessage.clearProperties();
                objectMessage.setStringProperty("nazivOperacije", "obrisiApartman");
                objectMessage.setObject(apartman);
                producer.send(Main.queue, objectMessage);
                System.out.println("Poslat je zahtev da se obrise apartman "+naziv+".");
                Thread.sleep(sekunde * 1000);
            } catch (Exception e) {
                Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
}
