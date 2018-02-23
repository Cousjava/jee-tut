package com.sleightholme.jeetut.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class Manager {
    
    @PersistenceContext(name="jpaexample")
    EntityManager em;
    
    public EntityManager getEntityManager(){
        return em;
    }
    
}
