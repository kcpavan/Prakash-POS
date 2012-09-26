/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Repository("KCPCommonDaoImpl")
@Service
public class commonDaoImpl implements commonDao{
    
     private static final Log log = LogFactory.getLog(ItemsDaoImpl.class);

	
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List getLookUpValues(Class cls, String propertyName)
			 {
		List list = null;
                Session session=null;
		
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		
		try {

			session=(Session)entityManager.getDelegate();
			
			Criteria criteria = session.createCriteria(cls).setCacheable(true)
					.addOrder(Order.asc(propertyName));
			list = criteria.list();
		 
		}catch (Exception e) {
			e.printStackTrace();
			log.error("\n\nException in\t\t" + methodName, e);
			
		} finally {
			log.info("\n\nInside Final block in\t\t" + methodName + "\n\n");
			if (session != null && session.isOpen()) {
				session.clear();
			}
		}
		return list;
	}

    
}
