/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.Zaalpersoneel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wim
 */
@Stateless
public class ZaalpersoneelFacade extends AbstractFacade<Zaalpersoneel> {

    @PersistenceContext(unitName = "2APPBIT1_Naudts_Wim_restaurant2018PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZaalpersoneelFacade() {
        super(Zaalpersoneel.class);
    }
    
}
