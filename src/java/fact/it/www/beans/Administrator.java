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
public class Administrator extends ExtraTaak{
    @Override
    public void update(){
        super.update();
        System.out.println("Vervolgens resgistreer ik de " + IngangTeller.getInstance().getAantal() + " klanten in het klantenbestand");
    }
}
