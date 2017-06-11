/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gdin Ješić
 */
@Entity
@Table(name = "rezervacija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r")
    , @NamedQuery(name = "Rezervacija.findById", query = "SELECT r FROM Rezervacija r WHERE r.id = :id")
    , @NamedQuery(name = "Rezervacija.findByDatumodjave", query = "SELECT r FROM Rezervacija r WHERE r.datumodjave = :datumodjave")
    , @NamedQuery(name = "Rezervacija.findByDatumprijave", query = "SELECT r FROM Rezervacija r WHERE r.datumprijave = :datumprijave")})
public class Rezervacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "datumodjave")
    @Temporal(TemporalType.DATE)
    private Date datumodjave;
    @Column(name = "datumprijave")
    @Temporal(TemporalType.DATE)
    private Date datumprijave;
    @JoinColumn(name = "idapartmanisobe", referencedColumnName = "id")
    @ManyToOne
    private Apartmanisobe idapartmanisobe;
    @OneToMany(mappedBy = "idrezervacija")
    private Collection<Kupac> kupacCollection;

    public Rezervacija() {
    }

    public Rezervacija(Apartmanisobe idapartmanisobe,Date datumod,Date datumdo) {
        this.idapartmanisobe=idapartmanisobe;
        this.datumodjave=datumdo;
        this.datumprijave=datumod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatumodjave() {
        return datumodjave;
    }

    public void setDatumodjave(Date datumodjave) {
        this.datumodjave = datumodjave;
    }

    public Date getDatumprijave() {
        return datumprijave;
    }

    public void setDatumprijave(Date datumprijave) {
        this.datumprijave = datumprijave;
    }

    public Apartmanisobe getIdapartmanisobe() {
        return idapartmanisobe;
    }

    public void setIdapartmanisobe(Apartmanisobe idapartmanisobe) {
        this.idapartmanisobe = idapartmanisobe;
    }

    @XmlTransient
    public Collection<Kupac> getKupacCollection() {
        return kupacCollection;
    }

    public void setKupacCollection(Collection<Kupac> kupacCollection) {
        this.kupacCollection = kupacCollection;
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
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Rezervacija[ id=" + id + " ]";
    }
    
}
