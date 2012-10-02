/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.ApplicationMain;
import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.dao.ItemDetailsDao;
import com.kcp.pos.data.ItemDetailsDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.ItemDetails;
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
    
    @Autowired
    private ItemDetailsDao itemDetailsDao;
    @Autowired
    private InvoiceService invoiceService;

    public ItemDetailsDao getItemDeItemDao() {
        return itemDetailsDao;
    }

    public void setItemDeItemDao(ItemDetailsDao itemDetailsDao) {
        this.itemDetailsDao = itemDetailsDao;
    }
    
    
    

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }  
    
    public void itemSave(Items item){
        itemDao.persist(item);
    }
    
    public void itemDetailsSave(ItemDetails itemDetails){
        itemDetailsDao.persist(itemDetails);
    }
    
    
    
    public List<ItemDo> getAllItemsDo(){
   List<ItemDo> itemDos = new ArrayList<ItemDo>();
        for (Items items : itemDao.findByAll()) {
           itemDos.add(new ItemDo(items)); 
        }
    return itemDos;
    }
    
     public List<Items> getAllItems(){
        return itemDao.findByAll();
    }
    
    public Items getItemByName(String name)
    {
        return(itemDao.findByName(name));
    }
    
     public Items getItemById(Integer Id)
    {
        return(itemDao.findById(Id));
    }
    
    public ItemDetailsDo getItemDetailsDoByItemId(Integer Id)
    {
        
        return(new ItemDetailsDo(itemDetailsDao.findByItemIdBillingType(Id,1)));
    }
    
    public List<ItemDetailsDo> getItemDetailsByItemId(Integer id)
    {
         List<ItemDetailsDo> itemDetailsDos = new ArrayList<ItemDetailsDo>();
        for (ItemDetails items : itemDetailsDao.findByItemId(id)) {
           itemDetailsDos.add(new ItemDetailsDo(items)); 
        }
    return itemDetailsDos;
       
    }
     
     public Boolean getAllItemDetailsByItemId(Integer id)
    {
         List<ItemDetailsDo> itemDetailsDos = new ArrayList<ItemDetailsDo>();
         invoiceService = (InvoiceService) ApplicationMain.springContext.getBean("invoiceService");
         List<InvoiceDetails> invoiceDetailses=null;
         
        for (ItemDetails items : itemDetailsDao.findAllByItemId(id)) {
            invoiceDetailses=invoiceService.getInvoiceDetailsById(items.getIdPk());
            if(invoiceDetailses!=null && invoiceDetailses.size()>0)
         {
             System.out.println("item id:"+items.getIdPk()
                     );
             return true;
         }
        }
    return false;
       
    }
     
     public ItemDetails getItemDetailsByItemIdBillingType(Integer Id,Integer type)
    {
        
        return(itemDetailsDao.findByItemIdBillingType(Id,1));
    }
    
    
    public Double getBillingPriceByItemId(Integer id)
    {
        return itemDetailsDao.findBillingPriceByItemId(id);
    }
    
   
}
