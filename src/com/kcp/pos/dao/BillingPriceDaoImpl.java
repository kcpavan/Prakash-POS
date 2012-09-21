/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingPrice;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.Items;
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
public class BillingPriceDaoImpl implements BillingPriceDao{
    
    private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
        
    public BillingPrice findByIdQuantity(Integer itemId,Integer quantity) 
        {
            log.debug("getting Billing instance with id: " + itemId+" and quantity:"+quantity);
		try {
			Query instance = entityManager.createNamedQuery("BillingPrice.findByItemIdQuantity")
                                .setParameter("itemId", itemId).setParameter("quantity", quantity);
			log.debug("get successful");
			return (BillingPrice)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
        }
    
     public BillingPrice findByNameQuantity(Integer itemId,Integer quantity) 
        {
            log.debug("getting Billing instance with id: " + itemId+" and quantity:"+quantity);
		try {
			Query instance = entityManager.createNamedQuery("BillingPrice.findByNameQuantity")
                                .setParameter("name", itemId).setParameter("quantity", quantity);
			log.debug("get successful");
			return (BillingPrice)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
        }
        
        public List<BillingPrice> findByItemId(Integer itemId) {
		log.debug("getting Items instance with item id: " + itemId);
		try {
			Query instance = entityManager.createNamedQuery("BillingPrice.findByItemId")
                                .setParameter("name", itemId);
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
    
    
}
