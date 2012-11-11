/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Stocks;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Prakash
 */
@Repository("stocksDaoImpl")
@Service
public class StocksDaoImpl implements StocksDao{
    
    private static final Log log = LogFactory.getLog(StocksDaoImpl.class);
	private EntityManager entityManager;
        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

        @Transactional
	public void persist(Stocks transientInstance) {
		log.debug("persisting Stocks instance");
		try {
                        
			entityManager.merge(transientInstance);
                        entityManager.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Items persistentInstance) {
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
        
        
        public Stocks findByName(String itemName) 
        {
            log.debug("getting Stocks instance with name: " + itemName);
		try {
			Query instance = entityManager.createNamedQuery("Stocks.findByName").setParameter("name", itemName);
			log.debug("get successful");
			return (Stocks)instance.getSingleResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
        }
        
        
        public List<Stocks> findByAll() {
		//log.debug("getting Items instance with id: " + id);
		try {
			Query instance = entityManager.createNamedQuery("Stocks.findAll");
			log.debug("get successful");
			return instance.getResultList();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
        
        public List<Stocks> findByDate(Date date)
        {
            Query instance=null;
            try {
                System.out.println("selected date is:"+date);
                Calendar c15DaysAgo = Calendar.getInstance(); // 15 days ago
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date start_date = format.parse(date + " 00:00:00");
                Date end_date = format.parse(date + " 23:59:59");
                //2012-11-11 23:49:38
                new Date(date+" 00:00:00");
                
                instance = entityManager.
                                createNamedQuery("Stocks.findByDate")
                               // .setParameter("date", date+"%");
                                .setParameter("start_date", start_date)
                                .setParameter("end_date", end_date);
                        
			log.debug("get successful");
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
            catch(ParseException p)
            {
                p.printStackTrace();
            }
            return instance.getResultList();
        }
        
         public Stocks findByItemId(Integer itemId)
         {
           log.debug("getting Stocks instance with id: " + itemId);
		try {
			Query instance = entityManager.
                                createNamedQuery("Stocks.findByItemId")
                                .setParameter("itemId", itemId);
			log.debug("get successful");
			 //return instance==null? null : (Stocks)instance.getSingleResult();
                        //List stocks=(Stocks)instance.getResultList();
                        List<Stocks> elementList =new ArrayList<Stocks>();
                        elementList = instance.getResultList();
                         return elementList.isEmpty() ? null : elementList.get(0);
                        /*if(instance==null)
                        {
                            return null;
                        }
                        else
                        {
                            try
                            {
                             stocks=(Stocks)instance.getResultList();
                            }
                            catch(NoResultException  e)
                            {
                                System.out.println("NoResultException  exception!!!");
                            }
                            return stocks;
                        }*/
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}  
         }
}