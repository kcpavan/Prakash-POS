package com.kcp.pos.modal.home;


import com.kcp.pos.modal.Stocks;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Stocks.
 * @see com.kcp.pos.modal.Stocks
 */
public class StocksHome {

	private static final Log log = LogFactory.getLog(StocksHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Stocks transientInstance) {
		log.debug("persisting Stocks instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Stocks persistentInstance) {
		log.debug("removing Stocks instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Stocks merge(Stocks detachedInstance) {
		log.debug("merging Stocks instance");
		try {
			Stocks result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Stocks findById(Integer id) {
		log.debug("getting Stocks instance with id: " + id);
		try {
			Stocks instance = entityManager.find(Stocks.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
