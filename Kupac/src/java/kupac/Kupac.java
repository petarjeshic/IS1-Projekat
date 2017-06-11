/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupac;

import entiteti.Apartmanisobe;
import entiteti.Korisnik;
import entiteti.Rezervacija;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 *
 * @author gdin Ješić
 */
public class Kupac extends Thread {
    int id;
    String username;
    List<Apartmanisobe> spisak;
    Kupac(int i,String username) {
       this.username=username;
       this.id=i;
       spisak=new ArrayList<>();
    }
    @Override
    public void run(){
        System.out.println("Startovan je kupac broj: "+id);
        JMSContext context = Main.connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer =context.createConsumer(Main.topic);
        while(true){
            try {
                int idxImena = new Random().nextInt(Main.imena.length);
                String ime=Main.imena[idxImena];
                int sekunde = (int)(Math.random() * 6 + 10);
                //izmena KORISNIKA
                String napravi="izmeniKorisnika";
                ObjectMessage objectMessage = context.createObjectMessage();
                objectMessage.setStringProperty("nazivOperacije", napravi);
                Korisnik k=new Korisnik(username, "nesto", Main.imena[idxImena], "nesto", "nesto", "nesto");
                objectMessage.setObject(k);
                producer.send(Main.queue,objectMessage);
                System.out.println("Poslat je zahtev da se izmeni ime kupcu ciji je ID: "+this.id);
                Thread.sleep(sekunde * 1000);
                //spisak SOBA I APARTMANA
                objectMessage.setStringProperty("nazivOperacije","dohvatiSobeUapartmanima");
                objectMessage.setIntProperty("idK",this.id);
                producer.send(Main.queue,objectMessage);
                System.out.println("Poslat je zahtev da se dostavi spisak soba u apartmanima korisniku sa ID: "+this.id);
                Thread.sleep(sekunde * 1000);
                //dohvatanje spiska
                //context.setClientID("Kupac"+id);
                Message message=consumer.receive();
                if(message instanceof ObjectMessage){
                    Object object=((ObjectMessage)message).getObject();
                    spisak= (List<Apartmanisobe>) object;
                    System.out.println("Spisak ima "+spisak.size()+" elemenata.");
                    System.out.println("Kupac ciji je ID:"+this.id+" je primio spisak svih apartmana i soba.");
                    Thread.sleep(sekunde * 1000);
                    if(!spisak.isEmpty()){
                    Collections.shuffle(spisak);
                    Rezervacija rez=new Rezervacija();
                    rez.setIdapartmanisobe(new Apartmanisobe(spisak.get(0).getIdapartman(),spisak.get(0).getIdsoba()));
                    Date datum1=Date.from(Instant.now());
                    Date datum2=Date.from(Instant.now());
                    rez.setDatumprijave(datum1);
                    rez.setDatumodjave(datum2);
                    ObjectMessage object1=context.createObjectMessage();
                    object1.setStringProperty("nazivOperacije","napraviRezervaciju");
                    object1.setObject(rez);
                    producer.send(Main.queue, object1);
                    System.out.println("Kupac sa ID:"+this.id+" je poslao zahtev za rezervaciju.");
                    }
                    Thread.sleep(sekunde * 1000);
                }
            } catch (Exception e) { 
                Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
