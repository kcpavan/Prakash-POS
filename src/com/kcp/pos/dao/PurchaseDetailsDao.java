/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.PurchaseDetails;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface PurchaseDetailsDao {
    
    public void persist(PurchaseDetails transientInstance); 
    
    public List<PurchaseDetails> findListById(Integer id);
     
    
    
    
}
