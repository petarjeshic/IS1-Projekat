/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gdin Ješić
 */
@Entity
@Table(name = "apartmanisobe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apartmanisobe.findAll", query = "SELECT a FROM Apartmanisobe a")
    , @NamedQuery(name = "Apartmanisobe.findById", query = "SELECT a FROM Apartmanisobe a WHERE a.id = :id")})
public class Apartmanisobe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "idapartmanisobe")
    private Collection<Rezervacija> rezervacijaCollection;
    @JoinColumn(name = "idsoba", referencedColumnName = "id")
    @ManyToOne
    private Soba idsoba;
    @JoinColumn(name = "idapartman", referencedColumnName = "id")
    @ManyToOne
    private Apartman idapartman;

    public Apartmanisobe() {
    }

    public Apartmanisobe(Apartman idapartman,Soba idsoba) {
        this.idapartman=idapartman;
        this.idsoba=idsoba;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Rezervacija> getRezervacijaCollection() {
        return rezervacijaCollection;
    }

    public void setRezervacijaCollection(Collection<Rezervacija> rezervacijaCollection) {
        this.rezervacijaCollection = rezervacijaCollection;
    }

    public Soba getIdsoba() {
        return idsoba;
    }

    public void setIdsoba(Soba idsoba) {
        this.idsoba = idsoba;
    }

    public Apartman getIdapartman() {
        return idapartman;
    }

    public void setIdapartman(Apartman idapartman) {
        this.idapartman = idapartman;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartmanisobe)) {
            return false;
        }
        Apartmanisobe other = (Apartmanisobe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Apartmanisobe[ id=" + id + " ]";
    }
    
}
