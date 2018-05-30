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
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Wim
 */
@Named(value = "personeelController")
@SessionScoped
public class PersoneelController implements Serializable{

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
    
    public List<Personeel> findAll(){
        return this.personeelFacade.findAll();
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
        Zaalpersoneel arno = new Zaalpersoneel("Arno");
        Zaalpersoneel tijmen = new Zaalpersoneel("Tijmen");
        Keukenpersoneel tom = new Keukenpersoneel("Tom");
        Keukenpersoneel bram = new Keukenpersoneel("Bram");
        Zaalpersoneel dieter = new Zaalpersoneel("Dieter");

        //we koppelen de spelers en scheidsrechter als observer aan de bal
        klantTeller.attachObserver(arno);
        klantTeller.attachObserver(tijmen);
        klantTeller.attachObserver(tom);
        klantTeller.attachObserver(bram);
        klantTeller.attachObserver(dieter);
        this.personeelFacade.create(arno);
        this.personeelFacade.create(tijmen);
        this.personeelFacade.create(tom);
        this.personeelFacade.create(bram);
        this.personeelFacade.create(dieter);

        System.out.println("####################################################################");
        System.out.println("Na het toevoegen van de observers...");
        //bal van positie veranderen
        klantTeller.setAantal(5);
        //lege lijn
        System.out.println();
        //we doen enkele observers weg
        klantTeller.detachObserver(bram);
        klantTeller.detachObserver(dieter);

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
        Zaalpersoneel johan = new Zaalpersoneel("Johan");
        ingangTeller.attachObserver(johan);
        ingangTeller.setAantal(7);
        // we gaan manu detachen en hem als poetspersoon attachen zodat hij nog altijd kan reageren op de klantenteller maar daarbij ook kan schoonmaken
        System.out.println("####################################################################");
        ingangTeller.detachObserver(johan);
        ingangTeller.setAantal(10);
        PoetsPersoon poetsPersoon = new PoetsPersoon();
        poetsPersoon.setPersoneel(johan);
        poetsPersoon.schoonMaken();
        // Manu moet nu ook nog de administratie erbij nemen als iemand binnenkomt
        System.out.println("####################################################################");
        Administrator administrator = new Administrator();
        administrator.setPersoneel(johan);
        ingangTeller.attachObserver(administrator);
        ingangTeller.setAantal(5);
        System.out.println("####################################################################");

        return "index";
    }
}
