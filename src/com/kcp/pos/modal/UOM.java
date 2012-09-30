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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Prakash
 */
@Entity
@Table(name = "uom", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "UOM.findAll", query = "SELECT c FROM uom c")
    
})
public class UOM {
    
    Integer idPk;
    String uomDesc;
    
    public UOM()
    {
        
    }
            

    public UOM(Integer idPk, String uomDesc) {
        this.idPk = idPk;
        this.uomDesc = uomDesc;
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

    @Column(name = "uom_desc", nullable = false, length = 250)
    public String getUomDesc() {
        return uomDesc;
    }

    public void setUomDesc(String uomDesc) {
        this.uomDesc = uomDesc;
    }
    
    
    
}
