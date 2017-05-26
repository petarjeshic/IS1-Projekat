/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menadzer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 *
 * @author Stefan
 */
public class Primalac extends Thread{
    public void run(){
        System.out.println("Startovan je primalac");
        
        JMSContext context = MenadzerMain.connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(MenadzerMain.queue);
        
        while(true){
            Message message = consumer.receive();
            if(message instanceof TextMessage){
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String sadrzaj = textMessage.getText();
                    int idR = textMessage.getIntProperty("idR");
                    String imeZadatka = textMessage.getStringProperty("imeZadatka");
                    System.out.println("Primljeno je resnje za zadatak '" + imeZadatka + "' od radnika " + idR + " sa sadrzajem: " + textMessage.getText());
                } catch (JMSException ex) {
                    Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
