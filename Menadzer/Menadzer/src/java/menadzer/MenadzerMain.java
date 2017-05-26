/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menadzer;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author Stefan
 */
public class MenadzerMain {

    @Resource(lookup = "MyQueue")
    static Queue queue;
    @Resource(lookup = "MyTopic")
    static Topic topic;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    public static void main(String[] args) {
        int brRadnika = Integer.parseInt(args[0]);
        Posiljalac posiljalac = new Posiljalac(brRadnika);
        posiljalac.start();
        
        Primalac primalac = new Primalac();
        primalac.start();
    }
    
}
