/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import java.util.List;

/**
 *
 * @author Prakash
 */
public interface commonDao {
     public List getLookUpValues(Class cls, String propertyName);
}
