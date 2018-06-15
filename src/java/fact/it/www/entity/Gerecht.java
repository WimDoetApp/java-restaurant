/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Wim
 */
@Entity
public class Gerecht implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String naam;
    private double actuelePrijs;
    @OneToMany(mappedBy = "gerecht")
    private List<BesteldItem> besteldeItems = new ArrayList<BesteldItem>();
    @ManyToMany(mappedBy = "gerechten")
    private List<MenuKaart> menuKaarten = new ArrayList<>();
    
    public Gerecht(){
        
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getActuelePrijs() {
        return actuelePrijs;
    }

    public void setActuelePrijs(double actuelePrijs) {
        this.actuelePrijs = actuelePrijs;
    }

    public List<BesteldItem> getBesteldeItems() {
        return besteldeItems;
    }

    public void setBesteldeItems(List<BesteldItem> besteldeItems) {
        this.besteldeItems = besteldeItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MenuKaart> getMenuKaarten() {
        return menuKaarten;
    }

    public void setMenuKaarten(List<MenuKaart> menuKaarten) {
        this.menuKaarten = menuKaarten;
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
        if (!(object instanceof Gerecht)) {
            return false;
        }
        Gerecht other = (Gerecht) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.Gerecht[ id=" + id + " ]";
    }
    
}
