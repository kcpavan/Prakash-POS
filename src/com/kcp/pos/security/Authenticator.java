/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.security;

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
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }


}
