package com.kcp.pos.modal.home;


import com.kcp.pos.modal.ItemCategory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ItemCategory.
 * @see com.kcp.pos.modal.ItemCategory
 * @author K.C.Pavan
 */
public class ItemCategoryHome {

	private static final Log log = LogFactory.getLog(ItemCategoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ItemCategory transientInstance) {
		log.debug("persisting ItemCategory instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ItemCategory persistentInstance) {
		log.debug("removing ItemCategory instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ItemCategory merge(ItemCategory detachedInstance) {
		log.debug("merging ItemCategory instance");
		try {
			ItemCategory result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemCategory findById(Integer id) {
		log.debug("getting ItemCategory instance with id: " + id);
		try {
			ItemCategory instance = entityManager.find(ItemCategory.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
