/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.service;

import com.kcp.pos.dao.WeighingTypeDao;
import com.kcp.pos.dao.WeighingTypeDaoImpl;
import com.kcp.pos.modal.WeighingType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prakash
 */
@Service
public class WeighingTypeService {
 
    @Autowired
    private WeighingTypeDao dao=new WeighingTypeDaoImpl();
    
   
    
    

   
    public List<WeighingType> getByCategory(Integer id)
    {
      
       // List<WeighingType> weighingTypes=new ArrayList<WeighingType>();
                
       
        return dao.findListByCategory(id);
    }
    
    
}
