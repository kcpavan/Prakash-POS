/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingPrice;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface BillingPriceDao {
    
    public void persist(BillingPrice transientInstance); 
     public void merge(BillingPrice transientInstance); 
     public BillingPrice findById(Integer id) ;
     public BillingPrice findByItemQuantityId(Integer id,Double quantity) ;
     public List<BillingPrice> findByItemDetailsId(Integer id) ;
     
     
    
}
