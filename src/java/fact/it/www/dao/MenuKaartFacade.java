/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.dao;

import fact.it.www.entity.MenuKaart;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Wim
 */
@Stateless
public class MenuKaartFacade extends AbstractFacade<MenuKaart> {

    @PersistenceContext(unitName = "2APPBIT1_Naudts_Wim_restaurant2018PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuKaartFacade() {
        super(MenuKaart.class);
    }
    
    /**
     * Zoeken op jaar
     * @param jaar --> jaar waarop we zoeken
     * @return lijst van menukaarten
     */
    public List<MenuKaart> zoekOpJaar(int jaar){
        Query q = em.createNamedQuery("MenuKaart.zoekOpJaar");
        q.setParameter("jaar", jaar);
        return q.getResultList();
    }
    
}
