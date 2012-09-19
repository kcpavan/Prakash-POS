/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao;

import com.kcp.pos.modal.Users;

/**
 *
 * @author pbhimanna
 */
public interface UserDao  {
    public Users findById(Integer id);
}
