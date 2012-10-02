/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;


import com.kcp.pos.modal.ItemDetails;
import com.kcp.pos.modal.Items;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface ItemDetailsDao {

    public void persist(ItemDetails transientInstance); 
    public com.kcp.pos.modal.ItemDetails findByItemIdBillingType(Integer id,Integer type) ;
    public List<ItemDetails>  findByItemId(Integer id) ;
    public List<ItemDetails>  findAllByItemId(Integer id) ;
    
    public Double findBillingPriceByItemId(Integer id) ;
    //public Double findByItemId(Integer id) ;
    
    

}
