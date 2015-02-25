/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import static com.xplicit.model.Client.FIND_ALL;
import static com.xplicit.model.Product.FIND_BY_CATEGORY_NAME;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author johnny
 */
@Entity
@Cacheable
@NamedQueries({
            
             @NamedQuery(name = FIND_BY_CATEGORY_NAME, query = "SELECT p FROM Product WHERE p.category.name = :pname"),
             @NamedQuery(name = FIND_ALL, query = "SELECT p FROM Product p")
})
@XmlRootElement
public class Product implements Serializable{
    
    // ================= Attributes ==========================================
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Version
    @Column(name = "version")
    private int version;
    
    @Column(length = 30, nullable = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String name;
    
    @Column(length = 1000, nullable = false)
    @NotNull
    @Size(max = 1000)
    private String description;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_fk", nullable = false)
    @XmlTransient
    private Category category;
    
    
    // ============= Constants ================================================
    
    public static final String FIND_BY_CATEGORY_NAME = "Product.findByCatogeryName";
    public static final String FIND_ALL = "Product.findAll";

    // ============ Getters and Setters =======================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
    
}
