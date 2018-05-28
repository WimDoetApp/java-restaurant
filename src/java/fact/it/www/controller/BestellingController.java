/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.beans.HappyHourBetaling;
import fact.it.www.beans.NormaleBetaling;
import fact.it.www.dao.BestellingFacade;
import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.TafelFacade;
import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Tafel;
import java.util.GregorianCalendar;
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
    @EJB
    private TafelFacade tafelFacade;
    @EJB
    private GerechtFacade gerechtFacade;
    private Bestelling bestelling = new Bestelling();

    public TafelFacade getTafelFacade() {
        return tafelFacade;
    }

    public void setTafelFacade(TafelFacade tafelFacade) {
        this.tafelFacade = tafelFacade;
    }

    public BestellingFacade getBestellingFacade() {
        return bestellingFacade;
    }

    public void setBestellingFacade(BestellingFacade bestellingFacade) {
        this.bestellingFacade = bestellingFacade;
    }

    public GerechtFacade getGerechtFacade() {
        return gerechtFacade;
    }

    public void setGerechtFacade(GerechtFacade gerechtFacade) {
        this.gerechtFacade = gerechtFacade;
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
    
    public String createTafels(){
        Tafel tafel = new Tafel();
        Tafel tafel2 = new Tafel();
        Tafel tafel3 = new Tafel();
        Tafel tafel4 = new Tafel();
        Tafel tafel5 = new Tafel();
        
        tafel.setCode("tafel1");
        tafel2.setCode("tafel2");
        tafel3.setCode("tafel3");
        tafel4.setCode("tafel4");
        tafel5.setCode("tafel5");
        
        tafelFacade.create(tafel);
        tafelFacade.create(tafel2);
        tafelFacade.create(tafel3);
        tafelFacade.create(tafel4);
        tafelFacade.create(tafel5);
        
        return "index";
    }
    
    public String testStrategyPatroon() {
        System.out.println("####################################################################");
        //Betalingstrategieën aanmaken
        HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
        NormaleBetaling normaleBetaling = new NormaleBetaling();
        //gerechten aanmaken
        Gerecht stoofvlees = new Gerecht();
        stoofvlees.setNaam("Stoofvlees met frietjes");
        stoofvlees.setActuelePrijs(16.0);
        Gerecht steak = new Gerecht();
        steak.setNaam("Steak met frietjes");
        steak.setActuelePrijs(19.0);
        Gerecht spaghetti = new Gerecht();
        spaghetti.setNaam("spaghetti bolognese");
        spaghetti.setActuelePrijs(14.0);
        Gerecht pizza = new Gerecht();
        pizza.setNaam("pizza");
        pizza.setActuelePrijs(15.0);
        Gerecht kip = new Gerecht();
        kip.setNaam("kipfilet met frietjes");
        kip.setActuelePrijs(14.0);
        gerechtFacade.create(stoofvlees);
        gerechtFacade.create(steak);
        gerechtFacade.create(spaghetti);
        gerechtFacade.create(pizza);
        gerechtFacade.create(kip);

        //maak bestelling met bestelitems
        Bestelling bestelling = new Bestelling();
        Bestelling bestelling2 = new Bestelling();
        Bestelling bestelling3 = new Bestelling();
        Bestelling bestelling4 = new Bestelling();
        Bestelling bestelling5 = new Bestelling();
        //NORMAAL
        bestelling.setBetaalStrategie(normaleBetaling);
        bestelling.setDatum(new GregorianCalendar());
        bestelling.addItem(stoofvlees, 2);
        bestelling.addItem(steak, 2);
        
        bestelling2.setBetaalStrategie(normaleBetaling);
        bestelling2.setDatum(new GregorianCalendar());
        bestelling2.addItem(spaghetti, 4);

        //HAPPYHOUR
        bestelling.setBetaalStrategie(happyHourBetaling);
        bestelling.addItem(stoofvlees, 2);
        bestelling.addItem(steak, 2);
        
        bestelling3.setBetaalStrategie(happyHourBetaling);
        bestelling3.setDatum(new GregorianCalendar());
        bestelling3.addItem(pizza, 2);
        bestelling3.addItem(kip, 1);
        
        bestelling5.setBetaalStrategie(happyHourBetaling);
        bestelling5.setDatum(new GregorianCalendar());
        bestelling5.addItem(pizza, 2);
        bestelling5.addItem(spaghetti, 1);

        //NORMAAL
        bestelling.setBetaalStrategie(normaleBetaling);
        bestelling.addItem(stoofvlees, 2);
        bestelling.addItem(steak, 2);
        
        bestelling4.setBetaalStrategie(normaleBetaling);
        bestelling4.setDatum(new GregorianCalendar());
        bestelling4.addItem(steak, 2);
        bestelling4.addItem(kip, 1);
        
        bestelling5.setBetaalStrategie(normaleBetaling);
        bestelling5.addItem(stoofvlees, 3);

        bestelling.maakRekening();
        bestelling2.maakRekening();
        bestelling3.maakRekening();
        bestelling4.maakRekening();
        bestelling5.maakRekening();
        System.out.println("####################################################################");
        
        //persistent maken
        this.bestellingFacade.create(bestelling);
        this.bestellingFacade.create(bestelling2);
        this.bestellingFacade.create(bestelling3);
        this.bestellingFacade.create(bestelling4);
        this.bestellingFacade.create(bestelling5);

        return "index";
    }
    
}
