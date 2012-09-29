/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.PurchaseDao;
import com.kcp.pos.dao.PurchaseDetailsDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.PurchaseDetailsDo;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Purchase;
import com.kcp.pos.modal.PurchaseDetails;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;
    
     @Autowired
    private PurchaseDetailsDao purchaseDetailsDao;
     
     
    
    
    
  public void purchaseSave(Purchase purchase){
        purchaseDao.persist(purchase);
    }

  public void purchaseDetailsSave(PurchaseDetails purchaseDetails){
        purchaseDetailsDao.persist(purchaseDetails);
    }

  
    public PurchaseDao getPurchaseDao() {
        return purchaseDao;
    }

    public void setPurchaseDao(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    public PurchaseDetailsDao getPurchaseDetailsDao() {
        return purchaseDetailsDao;
    }

    public void setPurchaseDetailsDao(PurchaseDetailsDao purchaseDetailsDao) {
        this.purchaseDetailsDao = purchaseDetailsDao;
    }
    
    public List<PurchaseDetailsDo> getPurchaseDetails(Integer purchaseId){
   List<PurchaseDetailsDo> purchaseDos = new ArrayList<PurchaseDetailsDo>();
   
   
      
        for (PurchaseDetails purchaseDetails : purchaseDetailsDao.findListById(purchaseId)) {
           purchaseDos.add(new PurchaseDetailsDo(purchaseDetails)); 
            //InvoiceDetailsDo invoice=new InvoiceDetailsDo();
            //invoice=invoiceDo.getInvoceDo(invoiceDetails);
            //invoiceDos.add(invoice);
        }
    return purchaseDos;
    }
    
}
