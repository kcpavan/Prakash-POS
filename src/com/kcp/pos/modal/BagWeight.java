/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author pbhimanna
 */
@Entity
@Table(name = "bag_weight", catalog = "storedb")
@NamedQueries({
  
    @NamedQuery(name = "BagWeight.findById", query = "SELECT c FROM BagWeight c "
        + "WHERE c.idPk = :id"),
    @NamedQuery(name = "BagWeight.findAll", query = "SELECT c FROM BagWeight c ")
})
public class BagWeight implements java.io.Serializable {

    private Integer idPk;
 
    private Double bagWeight;

    public BagWeight(Integer idPk, Double bagWeight) {
        this.idPk = idPk;
        this.bagWeight = bagWeight;
    }

    public BagWeight() {
    }
    
   
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_pk", unique = true, nullable = false)
    public Integer getIdPk() {
        return this.idPk;
    }

    public void setIdPk(Integer idPk) {
        this.idPk = idPk;
    }

    @Column(name = "bag_weight", nullable = false, precision = 22, scale = 0)
    public Double getBagWeight() {
        return bagWeight;
    }

    public void setBagWeight(Double bagWeight) {
        this.bagWeight = bagWeight;
    }

    
  

    
    
}
