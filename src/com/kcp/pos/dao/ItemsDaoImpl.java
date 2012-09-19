package com.kcp.pos.dao;


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
 * Home object for domain model class Items.
 * @see com.kcp.pos.modal.Items
 * @author K.C. Pavan
 */
@Repository("itemDaoImpl")
@Service
public class ItemsDaoImpl implements ItemDao {

	private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

        @Transactional
	public void persist(Items transientInstance) {
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

	public void remove(Items persistentInstance) {
		log.debug("removing Items instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Items merge(Items detachedInstance) {
		log.debug("merging Items instance");
		try {
			Items result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Items findById(Integer id) {
		log.debug("getting Items instance with id: " + id);
		try {
			Items instance = entityManager.find(Items.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        public List<Items> findByAll() {
		//log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("Items.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        
}
