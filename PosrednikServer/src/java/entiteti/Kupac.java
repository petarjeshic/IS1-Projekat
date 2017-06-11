/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gdin Ješić
 */
@Entity
@Table(name = "kupac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupac.findAll", query = "SELECT k FROM Kupac k")
    , @NamedQuery(name = "Kupac.findByBrkartice", query = "SELECT k FROM Kupac k WHERE k.brkartice = :brkartice")})
public class Kupac extends Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "brkartice")
    private String brkartice;
    @JoinColumn(name = "idrezervacija", referencedColumnName = "id")
    @ManyToOne
    private Rezervacija idrezervacija;

    public Kupac() {
    }

    
    public Kupac(String user, String pass, String name, String last, String phone, String email,String brkartice) {
        super(user,pass,name,last,phone,email);
        this.brkartice=brkartice;
    }
    public String getBrkartice() {
        return brkartice;
    }

    public void setBrkartice(String brkartice) {
        this.brkartice = brkartice;
    }

    public Rezervacija getIdrezervacija() {
        return idrezervacija;
    }

    public void setIdrezervacija(Rezervacija idrezervacija) {
        this.idrezervacija = idrezervacija;
    }

    
}
