/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.PurchaseDetails;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@Repository("purchaseDetailsDaoImpl")
@Service
public class PurchaseDetailsDaoImpl implements PurchaseDetailsDao{
    
      private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void persist(PurchaseDetails transientInstance)
    {
         
	
		log.debug("persisting Items instance");
		try {
                        
			entityManager.merge(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	
    }
    
    public List<PurchaseDetails> findListById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("PurchaseDetails.findAllById")
                                .setParameter("id", id);
                                
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
   
    
}
