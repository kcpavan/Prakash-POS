/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingType;
import com.kcp.pos.modal.Items;
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
@Repository("billingTypeDaoImpl")
@Service
public class BillingTypeDaoImpl implements BillingTypeDao {
    
    private static final Log log = LogFactory.getLog(BillingTypeDaoImpl.class);

	
	private EntityManager entityManager;
        
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
        @Transactional
     public BillingType findByName(String name) 
        {
            log.debug("getting Item instance with name: " + name);
		try {
                   
                    
			Query instance = entityManager.
                                createNamedQuery("BillingType.findByName")
                                .setParameter("name", name);
			log.debug("get successful");
			return (BillingType)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
        }
    
}
