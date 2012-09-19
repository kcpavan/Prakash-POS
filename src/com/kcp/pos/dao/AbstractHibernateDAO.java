/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author kcpavan
 */
public abstract class AbstractHibernateDAO< T extends Serializable >{
   private Class< T > clazz;
   
   @Autowired
   SessionFactory sessionFactory;
   
   public void setClazz( final Class< T > clazzToSet ){
      clazz = clazzToSet;
   }
   
   public T findOne( final Long id ){
      return (T) getCurrentSession().get( clazz, id );
   }
   public List< T > findAll(){
      return getCurrentSession()
       .createQuery( "from " + clazz.getName() ).list();
   }
   
   public void save( final T entity ){
      getCurrentSession().persist( entity );
   }
   
   public void update( final T entity ){
      getCurrentSession().merge( entity );
   }
   
   public void delete( final T entity ){
      getCurrentSession().delete( entity );
   }
   public void deleteById( final Long entityId ){
      final T entity = findOne( entityId );
      delete( entity );
   }
   
   protected final Session getCurrentSession(){
      return sessionFactory.getCurrentSession();
   }
}