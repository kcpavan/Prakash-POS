/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
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
@Repository("invoiceDaoImpl")
@Service
public class InvoiceDaoImpl implements InvoiceDao{
    
    private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public InvoiceDetails findDetailsById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
			InvoiceDetails instance = entityManager.find(InvoiceDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
   public Invoice findById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
                    Query instance = entityManager.createNamedQuery("Invoice.findById").setParameter("idPk", id);
			
			log.debug("get successful");
			return (Invoice)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
     public List<InvoiceDetails> findListById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("InvoiceDetails.findAllById")
                                .setParameter("id", id);
                                
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
     @Transactional
	public void persist(Invoice transientInstance) {
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
	public void merge(Invoice transientInstance) {
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
     
     public List<Invoice> findByAll() {
		try {
			Query instance = entityManager.createNamedQuery("Invoice.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
    
}
