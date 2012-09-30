/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.UOM;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface UOMDao {
    
    
    
    public List<UOM> findByAll() ;
    public UOM findByName(String itemName) ;
    
    public UOM findById(Integer id) ;
}
