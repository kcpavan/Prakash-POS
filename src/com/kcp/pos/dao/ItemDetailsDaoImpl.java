/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import java.util.ArrayList;
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
@Repository("itemDetailsDaoImpl")
@Service
class ItemDetailsDaoImpl implements ItemDetailsDao{
    
        private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);
    
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

        
    
    @Transactional
	public void persist(ItemDetails transientInstance) {
		log.debug("persisting Items instance");
		try {
                        
			entityManager.persist(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public ItemDetails findByItemIdBillingType(Integer id,Integer type) {
		log.debug("getting Items instance with id: " + id);
		try {
                    /*
                     * List<InvoiceDetails> elementList =new ArrayList<InvoiceDetails>();
                        elementList = instance.getResultList();
                        return elementList.isEmpty() ? null : elementList.get(0);

                     */
			//ItemDetails instance = entityManager.find(ItemDetails.class, id);
                    Query instance = entityManager.createNamedQuery("ItemDetails.findByItemIdBillingType")
                                .setParameter("itemId", id).setParameter("type", type);
			
                   List<ItemDetails> elementList =new ArrayList<ItemDetails>();
                        elementList = instance.getResultList();
                        return elementList.isEmpty() ? null : elementList.get(0);
                        
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

          
        
        public Double findBillingPriceByItemId(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
                    Query instance = entityManager.createNamedQuery("ItemDetails.findBillingPriceByItemId")
                                .setParameter("itemId", id);
			log.debug("get successful");
			List<ItemDetails> elementList =new ArrayList<ItemDetails>();
                        elementList = instance.getResultList();
                        return elementList.isEmpty() ? null : elementList.get(0).getBillingPrice();
                        
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        
        
    
}
