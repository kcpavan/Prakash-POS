/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Items;
import java.util.List;

/**
 *
 * @author kcpavan
 */
public interface ItemDao {
    
    public void persist(Items transientInstance); 
    public List<Items> findByAll() ;
    
}
