/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.ItemCategory;
import com.kcp.pos.modal.Items;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface ItemCategoryDao {
    
    public ItemCategory findById(Integer id);
    public ItemCategory findByName(String name);
    public List<ItemCategory> findByAll() ;
    
    
    
}
