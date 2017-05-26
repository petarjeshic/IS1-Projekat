/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menadzer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import zadaci.Zadatak;

/**
 *
 * @author Stefan
 */
public class Posiljalac extends Thread{

    int brRadnika;
    
    Posiljalac(int brRadnika) {
        this.brRadnika = brRadnika;
    }
    
    public void run(){
        System.out.println("Startovan je posiljalac");
        
        JMSContext context = MenadzerMain.connectionFactory.createContext();
        JMSProducer producer = context.createProducer();
        
        int i = 0;
        while(true){
            try {
                i++;
                int sekunde = (int)(Math.random() * 6 + 5);
                int op = (int)(Math.random() * 3);
                Zadatak.TipOperacije operacija = Zadatak.TipOperacije.SABIRANJE;
                String nazivOperacije = "sabiranje";
                switch(op){
                    case 0:
                        nazivOperacije = "sabiranje";
                        operacija = Zadatak.TipOperacije.SABIRANJE;
                        break;
                    case 1:
                        nazivOperacije = "oduzimanje";
                        operacija = Zadatak.TipOperacije.ODUZIMANJE;
                        break;
                    case 2:
                        nazivOperacije = "mnozenje";
                        operacija = Zadatak.TipOperacije.MNOZENJE;
                        break;
                }
                int a = (int)(Math.random() * 101);
                int b = (int)(Math.random() * 101);
                
                Thread.sleep(sekunde * 1000);
                
                int idR = (int)(Math.random() * brRadnika);
                ObjectMessage objectMessage = context.createObjectMessage();
                Zadatak zadatak = new Zadatak(a,b,operacija);
                objectMessage.setObject(zadatak);
                objectMessage.setIntProperty("idR", idR);
                String imeZadatka = "Zadatak" + i + " (a = " + a + ", b = " + b + ", operacija: " + nazivOperacije + ")";
                objectMessage.setStringProperty("imeZadatka", imeZadatka);
                producer.send(MenadzerMain.topic, objectMessage);
                System.out.println("Poslat je zadatak '" + imeZadatka + "' radniku " + idR);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Posiljalac.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JMSException ex) {
                Logger.getLogger(Posiljalac.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
