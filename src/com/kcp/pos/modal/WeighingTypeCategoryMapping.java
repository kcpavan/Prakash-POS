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
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Prakash
 */
@Entity
@Table(name = "category_weighingtype_mapping", catalog = "storedb")
@NamedQueries({
    @NamedQuery(name = "WeighingTypeCategoryMapping.findAll", query = "SELECT i FROM WeighingTypeCategoryMapping i"),
    @NamedQuery(name = "WeighingTypeCategoryMapping.findByCategory", query = "SELECT i.weighingType FROM WeighingTypeCategoryMapping i where i.category.idPk=:id")
})
public class WeighingTypeCategoryMapping {

    private Integer idPk;
    private WeighingType weighingType;
    private ItemCategory category;

    public WeighingTypeCategoryMapping() {
    }

    public WeighingTypeCategoryMapping(Integer idPk, WeighingType weighingType, ItemCategory category) {
        this.idPk = idPk;
        this.weighingType = weighingType;
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
    @JoinColumn(name = "weighing_id_fk", unique = true, nullable = false)
    public WeighingType getWeighingType() {
        return weighingType;
    }

    public void setWeighingType(WeighingType weighingType) {
        this.weighingType = weighingType;
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
