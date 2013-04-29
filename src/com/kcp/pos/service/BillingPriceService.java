/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.BillingPriceDao;
import com.kcp.pos.modal.BillingPrice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author Prakash
 */
@Service
public class BillingPriceService {
    
   @Autowired
    private BillingPriceDao BillingPriceDao;
   
  
    
  

    
    public void billingPriceSave(List<BillingPrice> billingPriceList){
        for(BillingPrice billingPrice:billingPriceList)
            BillingPriceDao.persist(billingPrice);
    }
    
    
            
    public void billingPriceUpdate(BillingPrice billingPrice){
        BillingPriceDao.merge(billingPrice);
    }
     
     
     
     
   
     
     public BillingPrice getBillingPriceById(Integer id){
        return BillingPriceDao.findById(id);
    }
     
    
     public List<BillingPrice> getByItemDetailsId(Integer id)
     {
         return BillingPriceDao.findByItemDetailsId(id);
     }
     
    
    
}
