/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.UOM;
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
@Repository("uomDaoImpl")
@Service
public class UOMDaoImpl implements UOMDao{
    
    
	private static final Log log = LogFactory.getLog(UOMDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

        
	
	

	public UOM findById(Integer id) {
		log.debug("getting UOM instance with id: " + id);
		try {
			UOM instance = entityManager.find(UOM.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        
        public UOM findByName(String itemName) 
        {
            log.debug("getting UOM instance with name: " + itemName);
		try {
			Query instance = entityManager.createNamedQuery("UOM.findByName").setParameter("name", itemName);
			log.debug("get successful");
			return (UOM)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
        }
        
        
        public List<UOM> findByAll() {
		//log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("UOM.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
    
}
