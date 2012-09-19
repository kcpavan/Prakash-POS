/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.InvoiceDao;
import com.kcp.pos.dao.ItemDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.InvoiceDo;
import com.kcp.pos.data.ItemDo;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Items;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author Prakash
 */
public class InvoiceService {
    
   @Autowired
    private InvoiceDao invoiceDao;

    public InvoiceDao getItemDao() {
        return invoiceDao;
    }

    public void setItemDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }  
    
    public void itemSave(Items item){
        invoiceDao.persist(item);
    }
  
    public List<InvoiceDetailsDo> getInvoiceItemsById(Integer invoiceId){
   List<InvoiceDetailsDo> invoiceDos = new ArrayList<InvoiceDetailsDo>();
        for (InvoiceDetails invoiceDetails : invoiceDao.findListById(invoiceId)) {
           invoiceDos.add(new InvoiceDetailsDo(invoiceDetails)); 
        }
    return invoiceDos;
    }
          
        
        

}
