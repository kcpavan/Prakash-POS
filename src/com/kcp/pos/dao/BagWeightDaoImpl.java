/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BagWeight;
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
 * @author pbhimanna
 */
@Repository("bagWeightDaoImpl")
@Service
public class BagWeightDaoImpl implements BagWeightDao {
    
     private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    
   public BagWeight findById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
                    Query instance = entityManager.createNamedQuery("BagWeight.findById").
                            setParameter("idPk", id);
			
			log.debug("get successful");
			return (BagWeight)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
   
   
   public List<BagWeight> findAll() {
		log.debug("getting all BagWeight instance");
		try {
                    Query instance = entityManager.createNamedQuery("BagWeight.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
   
    
   
    
     @Transactional
	public void persist(BagWeight transientInstance) {
		log.debug("persisting BagWeight instance");
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
	public void merge(BagWeight transientInstance) {
		log.debug("persisting BagWeight instance");
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
