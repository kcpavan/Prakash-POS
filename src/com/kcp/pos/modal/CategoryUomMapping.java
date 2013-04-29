/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import com.kcp.pos.modal.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Prakash
 */
@Entity
@Table(name = "category_uom_mapping", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "CategoryUomMapping.findAll", query = "SELECT i FROM CategoryUomMapping i"),
    @NamedQuery(name = "CategoryUomMapping.findByCategory", query = "SELECT i.uom FROM CategoryUomMapping i where i.category.idPk=:id")
})
public class CategoryUomMapping {

    private Integer idPk;
    private UOM uom;
    private ItemCategory category;

    public CategoryUomMapping() {
    }

    public CategoryUomMapping(Integer idPk, UOM uom, ItemCategory category) {
        this.idPk = idPk;
        this.uom = uom;
        this.category = category;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uom_id_fk", unique = true, nullable = false)
    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id_fk", unique = true, nullable = false)
    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}