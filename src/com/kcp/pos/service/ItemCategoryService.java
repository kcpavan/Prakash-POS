/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.ApplicationMain;
import com.kcp.pos.dao.ItemCategoryDao;
import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.dao.UserDao;
import com.kcp.pos.data.ItemCategoryDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.ItemCategory;
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
public class ItemCategoryService {
    @Autowired
    private ItemCategoryDao itemCategoryDao;

    public ItemCategoryDao getItemCategoryDao() {
        return itemCategoryDao;
    }

    public void setItemCategoryDao(ItemCategoryDao itemCategoryDao) {
        this.itemCategoryDao = itemCategoryDao;
    }

    public List<ItemCategoryDo> getAllItems(){
   List<ItemCategoryDo> itemDos = new ArrayList<ItemCategoryDo>();
   //ItemCategoryDao itemCategoryDao = (ItemCategoryDao) ApplicationMain.applicationContext.getBean("ItemCategoryDao");
        for (ItemCategory items : itemCategoryDao.findByAll()) {
           itemDos.add(new ItemCategoryDo(items)); 
        }
    return itemDos;
    } 
    
     public ItemCategory getItemCategoryByName(String name)
    {
        return(itemCategoryDao.findByName(name));
    }
    
    
    
}
