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
import fact.it.www.entity.BesteldItem;
import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Tafel;
import fact.it.www.entity.Zaalpersoneel;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Wim
 */
@Named(value = "bestellingController")
@SessionScoped
public class BestellingController implements Serializable{

    @EJB
    private BestellingFacade bestellingFacade;
    @EJB
    private TafelFacade tafelFacade;
    @EJB
    private GerechtFacade gerechtFacade;
    private Bestelling bestelling = new Bestelling();
    private List<Bestelling> bestellingen;
    private Zaalpersoneel zaalpersoneel = new Zaalpersoneel();
    private int jaar = Calendar.getInstance().get(Calendar.YEAR);
    private final List<String> betaalStrats = Arrays.asList("Normaal", "Happy Hour");
    protected HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
    protected NormaleBetaling normaleBetaling = new NormaleBetaling();
    
    /**
     * Creates a new instance of BestellingController
     */
    public BestellingController() {
    }

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

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }

    public Zaalpersoneel getZaalpersoneel() {
        return zaalpersoneel;
    }

    public void setZaalpersoneel(Zaalpersoneel zaalpersoneel) {
        this.zaalpersoneel = zaalpersoneel;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }
    
    public List<String> getBetaalStrats(){
        return betaalStrats;
    }
    
    /**
     * Lijst van alle tafels maken
     * @return lijst met tafels
     */
    public List<Tafel> findAllTafels(){
        return this.tafelFacade.findAll();
    }
    
    /**
     * Lijst van alle gerechten maken
     * @return lijst met gerehcten
     */
    public List<Gerecht> findAllGerechten(){
        return this.gerechtFacade.findAll();
    }
    
    /**
     * Lijst van alle bestellingen maken en weergeven
     * @return naam van de view
     */
    public String findAll(){
        bestellingen = this.bestellingFacade.findAll();
        
        return "bestellingen";
    }
    
    /**
     * Lijst van bestellingen via query weergeven
     * @param tafelId --> id van de tafel van de bestelling
     * @return naam van de view
     */
    public String zoekOpTafel(String tafelId){
        Long tafelIdLong = Long.parseLong(tafelId);

        bestellingen = bestellingFacade.zoekOpTafel(tafelIdLong);
        return "bestellingen";
    }
    
    /**
     * Lijst van bestellingen via query weergeven
     * @param dag --> dag van de bestellingen
     * @return naam van de view
     * @throws ParseException 
     */
    public String zoekOpDag(String dag) throws ParseException{
        bestellingen = bestellingFacade.zoekOpDag(dag);
        
        return "bestellingen";
    }
    
    /**
     * Lijst van bestellingen via query weergeen
     * @param maand --> maand van de bestellingen
     * @return naam van de view
     * @throws ParseException 
     */
    public String zoekOpMaand(String maand) throws ParseException{
        bestellingen = bestellingFacade.zoekOpMaand(maand);

        return "bestellingen";
    }
    
    /**
     * Lijst van bestellingen via query weergeven
     * @param jaar --> jaar van de bestellingen
     * @return naam van de view
     */
    public String zoekOpJaar(int jaar){
        bestellingen = bestellingFacade.zoekOpJaar(jaar);
        
        return "bestellingen";
    }
    
    /**
     * Details over een bepaalde bestelling weergeven
     * @param id --> van de bestelling
     * @return naam van de view
     */
    public String findBestelling(Long id){
        bestelling = this.bestellingFacade.find(id);
        
        System.out.println(bestelling.getId());
        
        return "detailBestelling";
    }
    
    /**
     * Naar de pagina gaan om een bestelling te maken
     * @param zaalpersoneel --> verantwoordelijke voor de bestelling
     * @return naam van de view
     */
    public String maakBestelling(Zaalpersoneel zaalpersoneel){
        this.zaalpersoneel = zaalpersoneel;
        
        bestelling = new Bestelling();
        
        return "maakBestelling";
    }
    
    public String addGerecht(int gerechtId, int aantal, String betaalStrat){      
        Gerecht gerecht = gerechtFacade.find((long)gerechtId);
        
        switch(betaalStrat){
            case "Normaal":
                bestelling.setBetaalStrategie(normaleBetaling);
                break;
            case "Happy Hour":
                bestelling.setBetaalStrategie(happyHourBetaling);
                break;
        }
        
        bestelling.addItem(gerecht, aantal);
       
        return "maakBestelling";
    }
    
    public String removeBesteldItem(int gerechtId, int aantal, double prijs){
        Gerecht gerecht = gerechtFacade.find((long)gerechtId);
        
        bestelling.removeItem(gerecht, aantal, prijs);
        
        return "maakBestelling";
    }
    
    public String opslaanBestelling(int tafelId){
        Tafel tafel = tafelFacade.find((long)tafelId);
        
        bestelling.setTafel(tafel);
        bestelling.setZaalpersoneel(zaalpersoneel);
        bestelling.setDatum(new GregorianCalendar());
        
        System.out.println(zaalpersoneel.getNaam());
        
        this.bestellingFacade.create(bestelling);
        
        return "homepage";
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
