/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Items;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
@Repository("invoiceDetailsDaoImpl")
@Service
public class InvoiceDetailsDaoImpl implements InvoiceDetailsDao {
    
    private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Transactional
    public void persist(InvoiceDetails transientInstance)
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
    
     public InvoiceDetails findByInvoiceItemId(Integer invoiceId,Integer itemId) 
    {
        log.debug("getting InvoiceDetails instance with ItemId: " + itemId);
		try {
			Query instance = entityManager.createNamedQuery("InvoiceDetails.findByInvoiceItemId")
                                .setParameter("itemId", itemId).setParameter("invoiceId", invoiceId);
			log.debug("get successful");
                        
                        List<InvoiceDetails> elementList =new ArrayList<InvoiceDetails>();
                        elementList = instance.getResultList();
                        return elementList.isEmpty() ? null : elementList.get(0);

                       /* Object result = instance.getSingleResult();
                        if(result==null)return null;
                        return (InvoiceDetails)result;*/
                        
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
    }
    
    /*public List<InvoiceDetails> findByAll() ;
    public InvoiceDetails findByName(String itemName) ;*/
    
}
