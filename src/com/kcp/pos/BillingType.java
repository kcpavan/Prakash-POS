/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos;

/**
 *
 * @author Prakash
 */
public class BillingType {
    
    private Integer idPk;
    private String typeDesc;

    public BillingType(Integer idPk, String typeDesc) {
        this.idPk = idPk;
        this.typeDesc = typeDesc;
    }

    public Integer getIdPk() {
        return idPk;
    }

    public void setIdPk(Integer idPk) {
        this.idPk = idPk;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
    
    
    
    
}
