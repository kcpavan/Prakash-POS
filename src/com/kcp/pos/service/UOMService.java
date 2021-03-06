/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.InvoiceDao;
import com.kcp.pos.dao.UOMDao;
import com.kcp.pos.dao.UOMDaoImpl;
import com.kcp.pos.data.InvoiceDetailsDo;
import com.kcp.pos.data.UOMDo;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.ItemCategory;
import com.kcp.pos.modal.UOM;
import com.kcp.pos.modal.WeighingType;
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
    private UOMDao uOMDao = new UOMDaoImpl();

    public List<UOMDo> getAllUOM() {
        List<UOMDo> uOMDos = new ArrayList<UOMDo>();
        for (UOM uom : uOMDao.findByAll()) {
            uOMDos.add(new UOMDo(uom));
        }
        return uOMDos;
    }

    public UOM getUOMByName(String name) {
        return (uOMDao.findByName(name));
    }
    
    public List<UOM> getByCategory(Integer id)
    {
       // List<WeighingType> weighingTypes=new ArrayList<WeighingType>();
        return uOMDao.findByCategory(id);
    }
}
