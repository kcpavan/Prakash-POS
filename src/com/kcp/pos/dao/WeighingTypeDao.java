/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.BillingType;
import com.kcp.pos.modal.WeighingType;
import java.util.List;

/**
 *
 * @author pbhimanna
 */
public interface WeighingTypeDao {
     public WeighingType findByName(String name) ;
     
     public List<WeighingType> findListByCategory(Integer id);
    
}
