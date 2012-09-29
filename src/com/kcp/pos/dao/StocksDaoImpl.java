/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Stocks;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Prakash
 */
@Repository("stocksDaoImpl")
@Service
public class StocksDaoImpl implements StocksDao{
    
    private static final Log log = LogFactory.getLog(StocksDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

        @Transactional
	public void persist(Stocks transientInstance) {
		log.debug("persisting Stocks instance");
		try {
                        
			entityManager.persist(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Items persistentInstance) {
		log.debug("removing Stocks instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Stocks merge(Stocks detachedInstance) {
		log.debug("merging Stocks instance");
		try {
			Stocks result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Stocks findById(Integer id) {
		log.debug("getting Stocks instance with id: " + id);
		try {
			Stocks instance = entityManager.find(Stocks.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        
        public Stocks findByName(String itemName) 
        {
            log.debug("getting Stocks instance with name: " + itemName);
		try {
			Query instance = entityManager.createNamedQuery("Stocks.findByName").setParameter("name", itemName);
			log.debug("get successful");
			return (Stocks)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
        }
        
        
        public List<Stocks> findByAll() {
		//log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("Stocks.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        
    
}
