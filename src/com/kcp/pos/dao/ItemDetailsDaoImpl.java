/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	public ItemDetails findByItemId(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
			ItemDetails instance = entityManager.find(ItemDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
    public void persist(com.kcp.pos.ItemDetails transientInstance) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
        
    
}
