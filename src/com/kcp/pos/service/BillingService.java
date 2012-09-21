/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.ApplicationMain;
import com.kcp.pos.dao.BillingPriceDao;
import com.kcp.pos.data.BillingPriceDo;
import com.kcp.pos.modal.BillingPrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class BillingService {
    
    //@Autowired
    private BillingPriceDao billingDao;

   
    
    public void billingPriceSave(BillingPrice billingPrice){
        billingDao.persist(billingPrice);
    }
    
    /*public List<BillingPriceDo> getAllItems(){
   List<BillingPriceDo> itemDos = new ArrayList<BillingPriceDo>();
        for (BillingPrice billingPrice : billingDao.findByAll()) {
           itemDos.add(new BillingPriceDo(billingPrice)); 
        }
    return itemDos;
    }*/
    
    public BillingPrice getBillingPrice(Integer itemId,Integer quantity)
    {
       //List<BillingPrice> billingPriceList=billingDao.findByItemId(itemId);
        billingDao=(BillingPriceDao) ApplicationMain.applicationContext.getBean("billingPriceDaoImpl");
      BillingPrice billingPrice=billingDao.findByIdQuantity(itemId,quantity);
      System.out.println("Billing price:"+billingPrice.getBillingPrice());
     // BillingPriceDo billingPriceDo=new BillingPriceDo(billingPrice);
      return billingPrice;
      
        
    }

    public BillingPriceDao getBillingDao() {
        return billingDao;
    }

    public void setBillingDao(BillingPriceDao billingDao) {
        this.billingDao = billingDao;
    }
    
}
