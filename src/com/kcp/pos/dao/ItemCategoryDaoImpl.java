/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.ItemCategory;
import com.kcp.pos.modal.Items;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Repository("itemCategoryDaoImpl")
@Service
public class ItemCategoryDaoImpl implements ItemCategoryDao {
    
     private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public ItemCategory findById(Integer id)
    {
        log.debug("getting category instance with id: " + id);
		try {
			ItemCategory instance = entityManager.find(ItemCategory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
    }
    
    public ItemCategory findByName(String name)
    {
        log.debug("getting category instance with name: " + name);
		try {
			Query instance = entityManager.createNamedQuery("ItemCategory.findByName")
                                .setParameter("name", name);
			log.debug("get successful");
			return (ItemCategory)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
    }
    
     public List<ItemCategory> findByAll() {
		//log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("ItemCategory.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
    
    
}
