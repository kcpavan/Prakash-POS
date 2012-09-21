/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.InvoiceDao;
import com.kcp.pos.dao.InvoiceDetailsDao;
import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.InvoiceDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
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
public class InvoiceService {
    
   @Autowired
    private InvoiceDao invoiceDao;
   
   @Autowired
   private InvoiceDetailsDao invoiceDetailsDao;
    
  

    
    public void invoiceSave(Invoice invoice){
        invoiceDao.persist(invoice);
    }
    
     public void invoiceDetailsSave(InvoiceDetails invoiceDetails){
        invoiceDetailsDao.persist(invoiceDetails);
    }
  
    public List<InvoiceDetailsDo> getInvoiceDetailsDoById(Integer invoiceId){
   List<InvoiceDetailsDo> invoiceDos = new ArrayList<InvoiceDetailsDo>();
   InvoiceDetailsDo invoiceDo=new InvoiceDetailsDo();
        for (InvoiceDetails invoiceDetails : invoiceDao.findListById(invoiceId)) {
           //invoiceDos.add(new InvoiceDetailsDo(invoiceDetails)); 
            InvoiceDetailsDo invoice=new InvoiceDetailsDo();
            invoice=invoiceDo.getInvoceDo(invoiceDetails);
            invoiceDos.add(invoice);
        }
    return invoiceDos;
    }
          
        
     public List<InvoiceDetails> getInvoiceDetailsById(Integer invoiceId){
        return invoiceDao.findListById(invoiceId);
    }
   

}