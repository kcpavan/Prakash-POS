/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.InvoiceDetails;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface InvoiceDetailsDao {
    
    public void persist(InvoiceDetails transientInstance); 
    /*public List<InvoiceDetails> findByAll() ;
    public InvoiceDetails findByName(String itemName) ;*/
    
}
