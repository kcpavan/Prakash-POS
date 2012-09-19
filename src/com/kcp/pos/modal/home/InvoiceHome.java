package com.kcp.pos.modal.home;


import com.kcp.pos.modal.Invoice;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Invoice.
 * @see com.kcp.pos.modal.Invoice
 * @author  K.C. Pavan
 */
public class InvoiceHome {

	private static final Log log = LogFactory.getLog(InvoiceHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Invoice transientInstance) {
		log.debug("persisting Invoice instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Invoice persistentInstance) {
		log.debug("removing Invoice instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Invoice merge(Invoice detachedInstance) {
		log.debug("merging Invoice instance");
		try {
			Invoice result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Invoice findById(Integer id) {
		log.debug("getting Invoice instance with id: " + id);
		try {
			Invoice instance = entityManager.find(Invoice.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
