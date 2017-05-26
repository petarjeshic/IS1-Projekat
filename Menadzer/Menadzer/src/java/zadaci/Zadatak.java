/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadaci;

import java.io.Serializable;

/**
 *
 * @author Stefan
 */
public class Zadatak implements Serializable{
    private int a;
    private int b;
    private TipOperacije operacija = TipOperacije.SABIRANJE;
    
    public enum TipOperacije{
        SABIRANJE, ODUZIMANJE, MNOZENJE
    }

    public Zadatak(int a, int b, TipOperacije operacija) {
        this.a = a;
        this.b = b;
        this.operacija = operacija;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public int radi(){
        switch(operacija){
            case SABIRANJE:
                return a + b;
            case ODUZIMANJE:
                return a - b;
            case MNOZENJE:
                return  a * b;
        }
        return a + b;
    }
}
