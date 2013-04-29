/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.security;

import com.kcp.pos.modal.UOM;
import com.kcp.pos.service.UOMService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kcpavan
 */
public class Authenticator {
    
 private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        USERS.put("krishna", "krishna");
    }
    public static boolean validate(String user, String password){
        
       /* UOMService service=new UOMService();
        UOM uom=service.getUOMByName(user);
        
        if(user==null)
        {
            return false;
        }
        else if(password==null)
        {
            return false;
        }
        else if(uom.getUserName().equalsIgnoreCase(user) && uom.getPassword().equalsIgnoreCase(password))
        {
            return true;
        }
        return false;*/
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }


}
