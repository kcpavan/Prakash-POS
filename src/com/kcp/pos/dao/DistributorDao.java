/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Distributor;
import com.kcp.pos.modal.Users;

/**
 *
 * @author Prakash
 */
public interface DistributorDao {
    public Distributor findById(Integer id);
    public void persist(Distributor transientInstance) ;
    public void remove(Distributor persistentInstance);
    public Distributor merge(Distributor detachedInstance);
}
