/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.InvoiceDao;
import com.kcp.pos.dao.InvoiceDetailsDao;
import com.kcp.pos.dao.StocksDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.InvoiceDo;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.PurchaseDetails;
import com.kcp.pos.modal.Stocks;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class StocksService {
    
      
   @Autowired
   private StocksDao stocksDao;
   
  
  public Stocks getStocksByItemId(Integer itemId)
  {
      return stocksDao.findByItemId(itemId);
  }

    
    public void saveStocks(Stocks stocks){
        stocksDao.persist(stocks);
    }
    
    
  public List<Stocks> getAllStocks()
  {
      return stocksDao.findByAll();
  }       
    
    
}
