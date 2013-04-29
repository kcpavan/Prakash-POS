/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Purchase;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface PurchaseDao {
    
    public void persist(Purchase transientInstance); 
    public void merge(Purchase transientInstance); 
    public List<Purchase> findByAll();    
     public List<Purchase> findByDate(java.sql.Date date); 
    /* public InvoiceDetails findById(Integer id) ;
     public List<InvoiceDetails> findListById(Integer id) ;*/
    
}
