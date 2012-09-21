/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.Items;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class ItemService {
 
    @Autowired
    private ItemDao itemDao;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }  
    
    public void itemSave(Items item){
        itemDao.persist(item);
    }
    
    public List<ItemDo> getAllItems(){
   List<ItemDo> itemDos = new ArrayList<ItemDo>();
        for (Items items : itemDao.findByAll()) {
           itemDos.add(new ItemDo(items)); 
        }
    return itemDos;
    }
    
    public Items getItemByName(String name)
    {
        return(itemDao.findByName(name));
    }
    
     public Items getItemById(Integer Id)
    {
        return(itemDao.findById(Id));
    }
    
    
    
}
