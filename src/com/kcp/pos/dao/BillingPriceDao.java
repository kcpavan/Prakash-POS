/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingPrice;
import com.kcp.pos.modal.Items;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface BillingPriceDao {
    
    public void persist(BillingPrice transientInstance); 
   // public List<BillingPrice> findByAll() ;
    public BillingPrice findByNameQuantity(Integer itemId,Integer quantity) ;
     public BillingPrice findByIdQuantity(Integer itemId,Integer quantity);
     public List<BillingPrice> findByItemId(Integer itemId);
    
}
