/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.beans;

/**
 *
 * @author Wim
 */
public class Zaalverantwoordelijke extends ExtraTaak{
    public void geefInstucties(){
        System.out.println("Zaalverantwoordelijke " + personeel.getNaam() + " geeft instructies aan het zaalpersoneel");
    }
    
    @Override
    public void update(){
        System.out.println("Zaalverantwoordelijke " + personeel.getNaam() + " heet de " + IngangTeller.getInstance().getAantal() + " klanten welkom!");
    }
}
