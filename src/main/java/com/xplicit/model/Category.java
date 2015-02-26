/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johnny
 */ 

@Entity
@Cacheable
@NamedQueries({
 
              @NamedQuery(name = Category.FIND_BY_NAME, query = "SELECT c FROM Category c WHERE c.name = :pname"),
              @NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c")
              })
@XmlRootElement
public class Category implements Serializable{
    
    // =================== Constants ====================================
   
    public static final String FIND_BY_NAME = "Category.findByName";
    public static final String FIND_ALL = "Category.findAll";
    
    // ================= Attributes =====================================
    
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
    
    @Column(length = 3000, nullable = false)
    @NotNull
    @Size(min = 1, max = 3000)
    private String description;
    
    // ============== Constructors ======================================
    
    public Category() {
    }

    public Category(String name, String description) {
        
        this.name = name;
        this.description = description;
    }
    
    // ================= Getters and Setters ===========================
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
    
    
    // =================== Object overriden methods =====================
    
    @Override
    public int hashCode() {
        
        final int code = 24;
        int result = 1;
        result = code * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        
        Category other = (Category)obj;
        
        if (id != null) {
            
            if (!id.equals(other.id)) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString() {
        
        
        StringBuilder sb = new StringBuilder();
                sb.append(getClass().getSimpleName())
                .append(" ")
                .append(" : version : ")
                .append(version)
                .append(" : name : ")
                .append(name)
                .append(" : description : ")
                .append(description);
        
        return sb.toString();
        
    }
    
    
}
