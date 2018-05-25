/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.dao.BestellingFacade;
import fact.it.www.entity.Bestelling;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Wim
 */
@Named(value = "bestellingController")
@Dependent
public class BestellingController {

    @EJB
    private BestellingFacade bestellingFacade;
    private Bestelling bestelling = new Bestelling();

    public BestellingFacade getBestellingFacade() {
        return bestellingFacade;
    }

    public void setBestellingFacade(BestellingFacade bestellingFacade) {
        this.bestellingFacade = bestellingFacade;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    /**
     * Creates a new instance of BestellingController
     */
    public BestellingController() {
    }
    
}
