/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingPrice;
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
@Repository("billingPriceDaoImpl")
@Service
public class BillingPriceDaoImpl implements BillingPriceDao {
    
   private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    
   public BillingPrice findById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
                    Query instance = entityManager.createNamedQuery("BillingPrice.findById").setParameter("idPk", id);
			
			log.debug("get successful");
			return (BillingPrice)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
   
   
   public BillingPrice findByItemQuantityId(Integer id,Double quantity) {
		log.debug("getting Items instance with id: " + id);
		try {
                    Query instance = entityManager.createNamedQuery("BillingPrice."
                            + "findByItemQtyId")
                            .setParameter("idPk", id)
                            .setParameter("qty", quantity);
			
			log.debug("get successful");
			return (BillingPrice)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
   
    
     public List<BillingPrice> findByItemDetailsId(Integer itemDetailsId) {
		log.debug("getting Items instance with id: " + itemDetailsId);
		try {
			Query instance = entityManager.createNamedQuery("BillingPrice.findByItemDetailsId")
                                .setParameter("id", itemDetailsId);
                                
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
     @Transactional
	public void persist(BillingPrice transientInstance) {
		log.debug("persisting Invoice instance");
		try {
                        
			entityManager.persist(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
     
      @Transactional
	public void merge(BillingPrice transientInstance) {
		log.debug("persisting Invoice instance");
		try {
                        
			//entityManager.persist(transientInstance);
                        entityManager.merge(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
     
      
    
}
