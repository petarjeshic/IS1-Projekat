/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posrednik;

import entiteti.Apartman;
import entiteti.Apartmanisobe;
import entiteti.Korisnik;
import entiteti.Kupac;
import entiteti.Prodavac;
import entiteti.Rezervacija;
import entiteti.Soba;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author gdin Ješić
 */
public class Server extends Thread {
    private List<Apartmanisobe> lista=null;
    public Server(){
        this.lista=new ArrayList<>();
    }
    @Override
    public void run(){
        System.out.println("*********Startovan je server.**********");
        
        JMSContext context = Main.connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(Main.queue);
        JMSProducer producer =context.createProducer();
        
        while(true){
            Message message = consumer.receive();
            if(message instanceof ObjectMessage){
                try {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    String operacija =objectMessage.getStringProperty("nazivOperacije");
                    switch(operacija){
                        case "napraviRezervaciju":
                            Rezervacija r=(Rezervacija)objectMessage.getObject();
                            if(r!=null)
                            Main.napraviRezervaciju(r);
                            break;
                        case "izmeniKorisnika":
                            Korisnik k=(Korisnik) objectMessage.getObject();
                            if(k!=null)
                            Main.izmeniKorisnika(k);
                            break;
                        case "dodajApartman":
                            Apartman a1=(Apartman) objectMessage.getObject();
                            Main.dodajApartman(a1.getName(), a1.getState(), a1.getCity(), a1.getStreet(), a1.getNumber(), a1.getDescription());
                            break;
                        case "izmeniApartman":
                            Apartman a2=(Apartman) objectMessage.getObject();
                            Main.izmeniApartman(a2.getId(),a2.getDescription());
                            break;
                        case "obrisiApartman":
                            Apartman a3=(Apartman) objectMessage.getObject();
                            if(a3!=null)
                            Main.obrisiApartman(a3.getId());
                            break;
                        case "dodajSobuUapartman":
                            Apartman a4=(Apartman) objectMessage.getObject();
                            Soba s=null;
                            if(a4!=null)
                            s=(Soba) objectMessage.getObject();
                            if(s!=null)
                            Main.dodajSobuUapartman(a4, s.getBrosoba(), s.getOpis());
                            break;
                        case "izmeniSobu":
                            Soba s1=(Soba) objectMessage.getObject();
                            if(s1!=null)
                            Main.izmeniSobu(s1.getId(),s1.getOpis());
                            break;
                        case "obrisiSobu":
                            Soba s2=(Soba) objectMessage.getObject();
                            if(s2!=null)
                            Main.obrisiSobu(s2.getId());
                            break;
                        case "dohvatiSobeUapartmanima":
                            int idK=objectMessage.getIntProperty("idK");
                            
                            lista= Main.dohvatiSobeUapartmanima();
                            ObjectMessage object=context.createObjectMessage();
                            object.setObject((Serializable) lista);
                            object.setIntProperty("idK", idK);
                            producer.send(Main.topic,object);
                            System.out.println("Lista soba po apartmanima je poslata korisniku ciji je ID: "+idK);
                            break;
                        default: break;
                    }
                } catch (JMSException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
