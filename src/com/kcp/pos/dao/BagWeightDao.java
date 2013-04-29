/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BagWeight;
import com.kcp.pos.modal.BillingPrice;
import java.util.List;

/**
 *
 * @author pbhimanna
 */
public interface BagWeightDao {
    
     public void persist(BagWeight transientInstance); 
     public void merge(BagWeight transientInstance); 
     public BagWeight findById(Integer id) ;
     public List<BagWeight> findAll() ;
    
}
