/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import fact.it.www.beans.IngangTeller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Wim
 */
@DiscriminatorValue("Zaal")
@Entity
public class Zaalpersoneel extends Personeel implements Serializable {
    @OneToMany(mappedBy = "zaalpersoneel")
    private List<Bestelling> bestellingen = new ArrayList<Bestelling>();
    
    public Zaalpersoneel(){
    }

    public Zaalpersoneel(String naam) {
        super(naam);
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
    
    @Override
    public void update() {
     String zaalstring = "Ik ben " + getNaam() + " en ga het nodige doen om voor " + IngangTeller.getInstance().getAantal() + " klanten een tafel klaar te maken.";
     System.out.println(zaalstring); 	 
  }

}
