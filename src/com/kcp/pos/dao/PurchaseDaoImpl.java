/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.Purchase;
import com.kcp.pos.modal.PurchaseDetails;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Prakash
 */
@Repository("purchaseDaoImpl")
@Service
public class PurchaseDaoImpl implements PurchaseDao{
    
    private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
     @Transactional
	public void persist(Purchase transientInstance) {
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
	public void merge(Purchase transientInstance) {
		log.debug("persisting Invoice instance");
		try {
                        
			entityManager.merge(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
     
     
     
    public List<Purchase> findByAll()
    {
       log.debug("getting All purchase items ");
		try {
			Query instance = entityManager.createNamedQuery("Purchase.findByAll");
                                
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} 
    }
  
    
    public List<Purchase> findByDate(java.sql.Date date)
    {
       log.debug("getting All purchase items ");
		
                    
                    String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date start_date=null;
                Date end_date=null;
                Query instance=null;
            try {
                start_date = format.parse(date + " 00:00:00");
           
                end_date = format.parse(date + " 23:59:59");
                //2012-11-11 23:49:38
                 
                
                instance = entityManager.
                                createNamedQuery("Purchase.findByDate")
                               // .setParameter("date", date+"%");
                                .setParameter("start_date", start_date)
                                .setParameter("end_date", end_date);
                
                
			/*Query instance = entityManager.createNamedQuery("Purchase.findByDate")
                               // .setParameter("date", new java.sql.Timestamp(date.getTime()));
                                 .setParameter("date", date);*/
			log.debug("get successful");
			
                        } catch (ParseException ex) {
                Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
		 catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} 
                return instance.getResultList();
}

     
     
    
}
