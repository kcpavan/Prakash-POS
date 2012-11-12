/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.Stocks;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface StocksDao {
    
    public void persist(Stocks transientInstance); 
    public List<Stocks> findByAll() ;
    public Stocks findByName(String itemName) ;
    public Stocks findById(Integer id) ;
    public Stocks findByItemId(Integer itemId);
  		
    
}
