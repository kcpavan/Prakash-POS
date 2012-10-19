/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Items;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface InvoiceDao {
    
     public void persist(Invoice transientInstance); 
     public InvoiceDetails findById(Integer id) ;
     public List<InvoiceDetails> findListById(Integer id) ;
      public List<Invoice> findByAll() ;
    
     
    
}
