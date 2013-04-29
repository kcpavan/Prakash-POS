/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.BagWeightDao;
import com.kcp.pos.dao.BillingPriceDao;
import com.kcp.pos.data.BagWeightDo;
import com.kcp.pos.modal.BagWeight;
import com.kcp.pos.modal.BillingPrice;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class BagWeightService {

    @Autowired
    private BagWeightDao bagWeightDao;

    public void Save(List<BagWeight> billingPriceList) {
        for (BagWeight billingPrice : billingPriceList) {
            bagWeightDao.persist(billingPrice);
        }
    }

    public void update(BagWeight billingPrice) {
        bagWeightDao.merge(billingPrice);
    }

    public BagWeight getById(Integer id) {
        return bagWeightDao.findById(id);
    }

    public List<BagWeightDo> getAll() {
        List<BagWeightDo> list=new ArrayList<BagWeightDo>();
        for (BagWeight bagWeight : bagWeightDao.findAll()) {
            list.add(new BagWeightDo(bagWeight));
        }
        return list;
    }
    
}
