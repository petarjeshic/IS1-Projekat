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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "apartman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apartman.findAll", query = "SELECT a FROM Apartman a")
    , @NamedQuery(name = "Apartman.findById", query = "SELECT a FROM Apartman a WHERE a.id = :id")
    , @NamedQuery(name = "Apartman.findByCity", query = "SELECT a FROM Apartman a WHERE a.city = :city")
    , @NamedQuery(name = "Apartman.findByName", query = "SELECT a FROM Apartman a WHERE a.name = :name")
    , @NamedQuery(name = "Apartman.findByNumber", query = "SELECT a FROM Apartman a WHERE a.number = :number")
    , @NamedQuery(name = "Apartman.findByState", query = "SELECT a FROM Apartman a WHERE a.state = :state")
    , @NamedQuery(name = "Apartman.findByStreet", query = "SELECT a FROM Apartman a WHERE a.street = :street")})
public class Apartman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "city")
    private String city;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private Integer number;
    @Column(name = "state")
    private String state;
    @Column(name = "street")
    private String street;
    @JoinTable(name = "prodavciapartmani", joinColumns = {
        @JoinColumn(name = "apartmanid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "prodavacid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Korisnik> korisnikCollection;
    @OneToMany(mappedBy = "idapartman")
    private Collection<Apartmanisobe> apartmanisobeCollection;

    public Apartman() {
    }

    public Apartman(String naziv, String drzava, String grad, String ulica, Integer broj, String description) {
        name=naziv;
        state=drzava;
        city=grad;
        street=ulica;
        number=broj;
        this.description=description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlTransient
    public Collection<Korisnik> getKorisnikCollection() {
        return korisnikCollection;
    }

    public void setKorisnikCollection(Collection<Korisnik> korisnikCollection) {
        this.korisnikCollection = korisnikCollection;
    }

    @XmlTransient
    public Collection<Apartmanisobe> getApartmanisobeCollection() {
        return apartmanisobeCollection;
    }

    public void setApartmanisobeCollection(Collection<Apartmanisobe> apartmanisobeCollection) {
        this.apartmanisobeCollection = apartmanisobeCollection;
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
        if (!(object instanceof Apartman)) {
            return false;
        }
        Apartman other = (Apartman) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Apartman[ id=" + id + " ]";
    }
    
}
