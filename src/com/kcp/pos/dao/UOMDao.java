/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Items;
import com.kcp.pos.modal.UOM;
import com.kcp.pos.modal.WeighingType;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface UOMDao {
    
    
    
    public List<UOM> findByAll() ;
    public UOM findByName(String userName) ;
    
    public UOM findById(Integer id) ;
    public List<UOM> findByCategory(Integer id);
}
