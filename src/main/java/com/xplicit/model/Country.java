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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johnny
 */

@Entity
@Cacheable // entity and its state can be cached by the provider
@XmlRootElement
public class Country implements Serializable{
    
    
    // ============================= Attributes ===========================
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Version
    @Column(name = "version")
    private int version;
    
    @Column(length = 2, name = "iso_code", nullable = false)
    @NotNull
    @Size(min = 2, max = 2)
    private String isoCode;
    
    @Column(length = 80, nullable = false)
    @NotNull
    @Size(min = 2, max = 80)
    private String name;
    
    @Column(length = 80, name = "printable_name", nullable = false)
    @NotNull
    @Size(min = 2, max = 80)
    private String printableName;
    
    @Column(length = 3)
    @NotNull
    @Size(min = 3, max = 3)
    private String iso3;
    
    @Column(length = 3)
    @NotNull
    @Size(min = 3, max = 3)
    private String numCode;
    
    // ========================== Constructors ==============================
   
    public Country() {}

    public Country(String isoCode, String name, String printableName, String iso3, String numCode) {
        
        this.isoCode = isoCode;
        this.name = name;
        this.printableName = printableName;
        this.iso3 = iso3;
        this.numCode = numCode;
    }
    
    // ======================== Getters and Setters ==========================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrintableName() {
        return printableName;
    }

    public void setPrintableName(String printableName) {
        this.printableName = printableName;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }
    
    // ========================= Object overriden methods ===================
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
        if (!(obj instanceof Country)) {
            return false;
        }
        
        Country other = (Country)obj;
        
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
                .append(" : isoCode : ")
                .append(isoCode)
                .append(" : name : ")
                .append(name)
                .append(" : printableName : ")
                .append(printableName)
                .append(" : iso3 : ")
                .append(iso3)
                .append(" : numcode : ")
                .append(numCode);
        
        return sb.toString();
        
    }
    
}
