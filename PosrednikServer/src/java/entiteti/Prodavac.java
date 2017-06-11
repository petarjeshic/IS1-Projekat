/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "prodavac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prodavac.findAll", query = "SELECT p FROM Prodavac p")
    , @NamedQuery(name = "Prodavac.findByBrojterminala", query = "SELECT p FROM Prodavac p WHERE p.brojterminala = :brojterminala")})
public class Prodavac extends Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "brojterminala")
    private Integer brojterminala;

    public Prodavac() {
    }

    public Prodavac(String user, String pass, String name, String last, String phone, String email,Integer brojterminala) {
        super(user,pass,name,last,phone,email);
        this.brojterminala=brojterminala;
    }
    

    public Integer getBrojterminala() {
        return brojterminala;
    }

    public void setBrojterminala(Integer brojterminala) {
        this.brojterminala = brojterminala;
    }
    
}
