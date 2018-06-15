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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Wim
 */
@NamedQueries ({
    @NamedQuery(
        name = "MenuKaart.zoekOpJaar",
        query = "select m FROM MenuKaart m where (m.jaar) = :jaar"
    )
})
@Entity
public class MenuKaart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int jaar;
    private String seizoen;
    @ManyToMany
    @JoinTable(name="MENUKAART_GERECHT")
    private List<Gerecht> gerechten = new ArrayList<>();
    @ManyToOne
    private Keukenpersoneel keukenpersoneel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public String getSeizoen() {
        return seizoen;
    }

    public void setSeizoen(String seizoen) {
        this.seizoen = seizoen;
    }

    public List<Gerecht> getGerechten() {
        return gerechten;
    }

    public void setGerechten(List<Gerecht> gerechten) {
        this.gerechten = gerechten;
    }

    public Keukenpersoneel getKeukenpersoneel() {
        return keukenpersoneel;
    }

    public void setKeukenpersoneel(Keukenpersoneel keukenpersoneel) {
        this.keukenpersoneel = keukenpersoneel;
    }
    
    public void addGerecht(Gerecht gerecht){
        gerechten.add(gerecht);
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
        if (!(object instanceof MenuKaart)) {
            return false;
        }
        MenuKaart other = (MenuKaart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.MenuKaart[ id=" + id + " ]";
    }
    
}
