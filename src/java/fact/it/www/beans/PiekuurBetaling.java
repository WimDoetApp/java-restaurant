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
public class PiekuurBetaling implements BetaalStrategie{
    @Override
    public double getToegepastePrijs(double prijs){
        return prijs * 1.2;
    }
}
