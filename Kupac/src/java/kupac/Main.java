/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupac;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

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
    public static String[] usernameovi={"katica","mitar","evnoje","griza","zivka"};
    public static String[] imena={"Mika","Damir","Pelva","Prvoslav","Stojko","Krstonije","Stanimir","Vlajko","Sinan","Koban"};
    public static void main(String[] args) {
       for(int i=0;i<5;i++){
           Kupac k=new Kupac(i,usernameovi[i]);
           k.start();
       }
    }
    
}
