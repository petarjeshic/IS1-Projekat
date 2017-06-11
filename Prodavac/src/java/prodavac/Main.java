/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodavac;

import java.util.Random;
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
    
    public static String[] imena={"Jovo","Pavlica","Mirveta","Stevo","Toza","Djoko","Drvoljub","Strvimir","Borivoje","Svaler"};
    public static String[] stanovi={"jednosoban","dvosoban","trosoban","cetvorosoban"};
    public static String[] usernameovi={"krsto","mita","milojica"};
    
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
        Prodavac prodavac=new Prodavac(i,usernameovi[i]);
        prodavac.start();
        }    
    }
    
}
