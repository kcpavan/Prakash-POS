/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.ItemDetails;
import com.kcp.pos.modal.Items;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface ItemDetailsDao {

    public void persist(ItemDetails transientInstance); 
    public com.kcp.pos.modal.ItemDetails findByItemId(Integer id) ;

}
