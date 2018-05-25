/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.beans.Administrator;
import fact.it.www.beans.IngangTeller;
import fact.it.www.beans.PoetsPersoon;
import fact.it.www.dao.PersoneelFacade;
import fact.it.www.entity.Keukenpersoneel;
import fact.it.www.entity.Personeel;
import fact.it.www.entity.Zaalpersoneel;
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

    public PersoneelFacade getPersoneelFacade() {
        return personeelFacade;
    }

    public void setPersoneelFacade(PersoneelFacade personeelFacade) {
        this.personeelFacade = personeelFacade;
    }

    /**
     * Creates a new instance of PersoneelController
     */
    public PersoneelController() {
    }
    
    public String testSingletonPatroon() {
        System.out.println("####################################################################");
        IngangTeller it1  = IngangTeller.getInstance();
        IngangTeller it2; 
        it2 = IngangTeller.getInstance();
        if (it1 == it2) {
            System.out.println("De twee singletonvariabelen verwijzen naar hetzelfde object.");
        } else {
            System.out.println("Dit kan in principe niet.");
        }
        System.out.println("####################################################################");
        return "index";
    }
    
    public String testObserverPatroon() {
        IngangTeller klantTeller = IngangTeller.getInstance();

        //een paar personeelsleden
        Zaalpersoneel jan = new Zaalpersoneel("Jan");
        Zaalpersoneel piet = new Zaalpersoneel("Piet");
        Keukenpersoneel serge = new Keukenpersoneel("Serge");
        Keukenpersoneel jeroen = new Keukenpersoneel("Jeroen");

        //we koppelen de spelers en scheidsrechter als observer aan de bal
        klantTeller.attachObserver(jan);
        klantTeller.attachObserver(piet);
        klantTeller.attachObserver(serge);
        klantTeller.attachObserver(jeroen);
        this.personeelFacade.create(jan);
        this.personeelFacade.create(piet);
        this.personeelFacade.create(serge);
        this.personeelFacade.create(jeroen);

        System.out.println("####################################################################");
        System.out.println("Na het toevoegen van de observers...");
        //bal van positie veranderen
        klantTeller.setAantal(5);
        //lege lijn
        System.out.println();
        //we doen enkele observers weg
        klantTeller.detachObserver(piet);
        klantTeller.detachObserver(serge);

        System.out.println("Na het ontkoppelen van Piet en Serge ...");
        //we veranderen de bal weer van positie
        klantTeller.setAantal(3);
        System.out.println("####################################################################");
        return "index";
    }
    
    public String testDecoratorPatroon() {
        IngangTeller ingangTeller = IngangTeller.getInstance();
        // een nieuw zaalpersoneelslid toevoegen   
        System.out.println("####################################################################");
        Zaalpersoneel manu = new Zaalpersoneel("Manu");
        ingangTeller.attachObserver(manu);
        ingangTeller.setAantal(7);
        // we gaan manu detachen en hem als poetspersoon attachen zodat hij nog altijd kan reageren op de klantenteller maar daarbij ook kan schoonmaken
        System.out.println("####################################################################");
        ingangTeller.detachObserver(manu);
        ingangTeller.setAantal(10);
        PoetsPersoon poetsPersoon = new PoetsPersoon();
        poetsPersoon.setPersoneel(manu);
        poetsPersoon.schoonMaken();
        // Manu moet nu ook nog de administratie erbij nemen als iemand binnenkomt
        System.out.println("####################################################################");
        Administrator administrator = new Administrator();
        administrator.setPersoneel(manu);
        ingangTeller.attachObserver(administrator);
        ingangTeller.setAantal(5);
        System.out.println("####################################################################");

        return "index";
    }
}
