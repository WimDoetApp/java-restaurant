/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.dao.PersoneelFacade;
import fact.it.www.entity.Personeel;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Wim
 */
@Named(value = "personeelController")
@Dependent
public class PersoneelController {

    @EJB
    private PersoneelFacade personeelFacade;
    private Personeel personeel = new Personeel();

    public PersoneelFacade getPersoneelFacade() {
        return personeelFacade;
    }

    public void setPersoneelFacade(PersoneelFacade personeelFacade) {
        this.personeelFacade = personeelFacade;
    }

    public Personeel getPersoneel() {
        return personeel;
    }

    public void setPersoneel(Personeel personeel) {
        this.personeel = personeel;
    }

    /**
     * Creates a new instance of PersoneelController
     */
    public PersoneelController() {
    }
    
}
