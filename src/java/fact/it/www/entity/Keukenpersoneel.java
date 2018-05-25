/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import fact.it.www.beans.IngangTeller;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Wim
 */
@DiscriminatorValue("Keuken")
@Entity
public class Keukenpersoneel extends Personeel implements Serializable {
    public Keukenpersoneel(){
    }
    
    public Keukenpersoneel(String naam){
        super(naam);
    }
    
    @Override
    public void update(){
        String keukenString = "Ik ben " + getNaam() + " en ik begin onmiddelijk met het maken van " + IngangTeller.getInstance().getAantal() + " amuse-geules!";
        System.out.println(keukenString);
    }
}
