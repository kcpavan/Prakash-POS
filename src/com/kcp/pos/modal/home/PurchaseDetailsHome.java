package com.kcp.pos.modal.home;


import com.kcp.pos.modal.PurchaseDetails;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PurchaseDetails.
 * @see com.kcp.pos.modal.PurchaseDetails
 */
public class PurchaseDetailsHome {

	private static final Log log = LogFactory.getLog(PurchaseDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PurchaseDetails transientInstance) {
		log.debug("persisting PurchaseDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(PurchaseDetails persistentInstance) {
		log.debug("removing PurchaseDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public PurchaseDetails merge(PurchaseDetails detachedInstance) {
		log.debug("merging PurchaseDetails instance");
		try {
			PurchaseDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PurchaseDetails findById(Integer id) {
		log.debug("getting PurchaseDetails instance with id: " + id);
		try {
			PurchaseDetails instance = entityManager.find(
					PurchaseDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
