/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.BillingPriceDao;
import com.kcp.pos.dao.PurchaseDao;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Prakash
 */
public class PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;
    
  public void purchaseSave(Purchase purchase){
        purchaseDao.persist(purchase);
    }
    
}
