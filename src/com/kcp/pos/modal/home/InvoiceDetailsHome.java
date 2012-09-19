package com.kcp.pos.modal.home;


import com.kcp.pos.modal.InvoiceDetails;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class InvoiceDetails.
 * @see com.kcp.pos.modal.InvoiceDetails
 * @author K.C. Pavan
 */
public class InvoiceDetailsHome {

	private static final Log log = LogFactory.getLog(InvoiceDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(InvoiceDetails transientInstance) {
		log.debug("persisting InvoiceDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(InvoiceDetails persistentInstance) {
		log.debug("removing InvoiceDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public InvoiceDetails merge(InvoiceDetails detachedInstance) {
		log.debug("merging InvoiceDetails instance");
		try {
			InvoiceDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InvoiceDetails findById(Integer id) {
		log.debug("getting InvoiceDetails instance with id: " + id);
		try {
			InvoiceDetails instance = entityManager.find(InvoiceDetails.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
