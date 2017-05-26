/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radnik;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.*;
import zadaci.Zadatak;

/**
 *
 * @author Stefan
 */
public class Radnik {

    @Resource(lookup = "MyQueue")
    static Queue queue;
    @Resource(lookup = "MyTopic")
    static Topic topic;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    public static void main(String[] args) {
        int idR = Integer.parseInt(args[0]);
        System.out.println("Startovan radnik " + idR);
        
        JMSContext context = connectionFactory.createContext();
        context.setClientID("Radnik" + idR);
        JMSConsumer consumer = context.createDurableConsumer(topic, "Prijava" + idR, "idR = " + idR, false);
        JMSProducer producer = context.createProducer();
        
        while(true){
            int sekunde = (int)(Math.random() * 6 + 5);
            Message message = consumer.receive();
            
            if(message instanceof ObjectMessage){
                try {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    Zadatak zadatak = (Zadatak) objectMessage.getObject();
                    String imeZadatka = objectMessage.getStringProperty("imeZadatka");
                    int resenje = zadatak.radi();
                    
                    Thread.sleep(sekunde * 1000);
                    
                    TextMessage textMessage = context.createTextMessage();
                    textMessage.setText("Resenje: " + resenje);
                    textMessage.setIntProperty("idR", idR);
                    textMessage.setStringProperty("imeZadatka", imeZadatka);
                    producer.send(queue, textMessage);
                    System.out.println("Poslato je resenje za zadatak '" + imeZadatka + "' sa vrednoscu " + resenje);
                } catch (JMSException ex) {
                    Logger.getLogger(Radnik.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Radnik.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
