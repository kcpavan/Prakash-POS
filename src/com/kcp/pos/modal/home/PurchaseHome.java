package com.kcp.pos.modal.home;


import com.kcp.pos.modal.Purchase;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Purchase.
 * @see com.kcp.pos.modal.Purchase
 */
public class PurchaseHome {

	private static final Log log = LogFactory.getLog(PurchaseHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Purchase transientInstance) {
		log.debug("persisting Purchase instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Purchase persistentInstance) {
		log.debug("removing Purchase instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Purchase merge(Purchase detachedInstance) {
		log.debug("merging Purchase instance");
		try {
			Purchase result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Purchase findById(Integer id) {
		log.debug("getting Purchase instance with id: " + id);
		try {
			Purchase instance = entityManager.find(Purchase.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
