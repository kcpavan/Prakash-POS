package com.kcp.pos.modal.home;


import com.kcp.pos.modal.ItemDistributor;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Distributor.
 * @see com.kcp.pos.modal.Distributor
 * @author  K.C. Pavan
 */

public class DistributorHome {

	private static final Log log = LogFactory.getLog(DistributorHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ItemDistributor transientInstance) {
		log.debug("persisting Distributor instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ItemDistributor persistentInstance) {
		log.debug("removing Distributor instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ItemDistributor merge(ItemDistributor detachedInstance) {
		log.debug("merging Distributor instance");
		try {
			ItemDistributor result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemDistributor findById(Integer id) {
		log.debug("getting Distributor instance with id: " + id);
		try {
			ItemDistributor instance = entityManager.find(ItemDistributor.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
