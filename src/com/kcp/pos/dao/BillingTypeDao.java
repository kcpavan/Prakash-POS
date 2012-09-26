/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingType;
import com.kcp.pos.modal.Items;

/**
 *
 * @author Prakash
 */
public interface BillingTypeDao {
     public BillingType findByName(String name) ;
    
}
