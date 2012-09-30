/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.InvoiceDao;
import com.kcp.pos.dao.UOMDao;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.UOMDo;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.UOM;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class UOMService {
    
     @Autowired
    private UOMDao uOMDao;
     
      public List<UOMDo> getAllUOM(){
   List<UOMDo> uOMDos = new ArrayList<UOMDo>();
   
        for (UOM uom : uOMDao.findByAll()) {
           uOMDos.add(new UOMDo(uom)); 
            
        }
    return uOMDos;
    }
   
  
    
}
