/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.KeukenpersoneelFacade;
import fact.it.www.dao.MenuKaartFacade;
import fact.it.www.dao.PersoneelFacade;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Keukenpersoneel;
import fact.it.www.entity.MenuKaart;
import fact.it.www.entity.Zaalpersoneel;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Wim
 */
@Named(value = "menuKaartController")
@SessionScoped
public class MenuKaartController implements Serializable{

    @EJB
    private KeukenpersoneelFacade keukenpersoneelFacade;
    @EJB
    private MenuKaartFacade menuKaartFacade;
    @EJB
    private PersoneelFacade personeelFacade;
    @EJB
    private GerechtFacade gerechtFacade;
    private List<Keukenpersoneel> listKeukenpersoneel;
    private MenuKaart menuKaart;
    private List<MenuKaart> menuKaarten;

    public KeukenpersoneelFacade getKeukenpersoneelFacade() {
        return keukenpersoneelFacade;
    }

    public void setKeukenpersoneelFacade(KeukenpersoneelFacade keukenpersoneelFacade) {
        this.keukenpersoneelFacade = keukenpersoneelFacade;
    }

    public MenuKaartFacade getMenuKaartFacade() {
        return menuKaartFacade;
    }

    public void setMenuKaartFacade(MenuKaartFacade menuKaartFacade) {
        this.menuKaartFacade = menuKaartFacade;
    }

    public PersoneelFacade getPersoneelFacade() {
        return personeelFacade;
    }

    public void setPersoneelFacade(PersoneelFacade personeelFacade) {
        this.personeelFacade = personeelFacade;
    }

    public GerechtFacade getGerechtFacade() {
        return gerechtFacade;
    }

    public void setGerechtFacade(GerechtFacade gerechtFacade) {
        this.gerechtFacade = gerechtFacade;
    }

    public List<Keukenpersoneel> getListKeukenpersoneel() {
        return listKeukenpersoneel;
    }

    public void setListKeukenpersoneel(List<Keukenpersoneel> listKeukenpersoneel) {
        this.listKeukenpersoneel = listKeukenpersoneel;
    }

    public MenuKaart getMenuKaart() {
        return menuKaart;
    }

    public void setMenuKaart(MenuKaart menuKaart) {
        this.menuKaart = menuKaart;
    }

    public List<MenuKaart> getMenuKaarten() {
        return menuKaarten;
    }

    public void setMenuKaarten(List<MenuKaart> menuKaarten) {
        this.menuKaarten = menuKaarten;
    }
    
    /**
     * Creates a new instance of MenuKaartController
     */
    public MenuKaartController() {
    }
    
    /**
     * Lijst van al het keukenpersoneel ophalen
     * @return lijst met keukenpersoneel
     */
    public List<Keukenpersoneel> findAllKeukenpersoneel(){
        return this.keukenpersoneelFacade.findAll();
    }
    
    /**
     * Menukaart aanmaken
     * @param personeelId --> kok
     * @param jaartal
     * @param seizoen
     * @return view om gerechten aan de juist aangemaakte menukaart toe te voegen
     */
    public String maakMenuKaart(int personeelId, int jaartal, String seizoen){
        //nieuwe menukaart aanmaken
        menuKaart = new MenuKaart();
        
        Keukenpersoneel keukenpersoneel = keukenpersoneelFacade.find((long)personeelId);        
        //value's toewijzen
        menuKaart.setKeukenpersoneel(keukenpersoneel);
        menuKaart.setJaar(jaartal);
        System.out.println(jaartal);
        menuKaart.setSeizoen(seizoen);
        
        //opslaan in de db
        this.menuKaartFacade.create(menuKaart);
        
        return "gerechtToevoegen";
    }
    
    /**
     * Gerecht toevoegen aan de menukaart
     * @param gerechtId --> id van het gerecht dat we toevoeegen
     * @return view waar we van komen om nogmaals een gerecht toe te voegen
     */
    public String addGerecht(int gerechtId){
        Gerecht gerecht = gerechtFacade.find((long)gerechtId);
        
        menuKaart.addGerecht(gerecht);
        this.menuKaartFacade.edit(menuKaart);
        
        return "gerechtToevoegen";
    }
    
    /**
     * Overzicht van alle menukaarten weergeven
     * @return view met overzicht van menukaarten
     */
    public String overzichtMenuKaarten(){
        menuKaarten = this.menuKaartFacade.findAll();
        
        return "overzichtMenuKaarten";
    }
    
    /**
     * Menukaarten zoeken op jaar
     * @param jaar
     * @return view met overzicht van menukaarten
     */
    public String zoekOpJaar(int jaar){
        menuKaarten = this.menuKaartFacade.zoekOpJaar(jaar);
        
        return "overzichtMenuKaarten";
    }
}
