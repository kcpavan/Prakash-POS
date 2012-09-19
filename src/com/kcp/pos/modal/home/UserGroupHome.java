package com.kcp.pos.modal.home;


import com.kcp.pos.modal.UserGroup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UserGroup.
 * @see com.kcp.pos.modal.UserGroup
 */
public class UserGroupHome {

	private static final Log log = LogFactory.getLog(UserGroupHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserGroup transientInstance) {
		log.debug("persisting UserGroup instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserGroup persistentInstance) {
		log.debug("removing UserGroup instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserGroup merge(UserGroup detachedInstance) {
		log.debug("merging UserGroup instance");
		try {
			UserGroup result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserGroup findById(Integer id) {
		log.debug("getting UserGroup instance with id: " + id);
		try {
			UserGroup instance = entityManager.find(UserGroup.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
