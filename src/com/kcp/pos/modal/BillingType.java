/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Prakash
 */
@Entity
@Table(name = "billing_type", catalog = "storedb")
public class BillingType {
    
    private Integer idPk;
    private String typeDesc;
    
    public BillingType()
    {
        
    }

    public BillingType(Integer idPk, String typeDesc) {
        this.idPk = idPk;
        this.typeDesc = typeDesc;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_pk", unique = true, nullable = false)
    public Integer getIdPk() {
        return idPk;
    }

    public void setIdPk(Integer idPk) {
        this.idPk = idPk;
    }

    @Column(name = "type_desc", unique = true, nullable = false)
    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
    
    
    
    
}
