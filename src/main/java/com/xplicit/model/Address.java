/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 */
@Embeddable
public class Address implements Serializable {
    
    // ============= Attributes ===============================
    
    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    @NotNull
    private String street;
    
    @Column(length = 50, nullable = false)
    @Size(min = 2, max = 50)
    @NotNull
    private String city;
    
    @Column
    private String state;
    
    @Column(length = 10, name = "zip_code", nullable = false)
    @Size(min = 1, max = 10)
    @NotNull
    private String zipcode;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Country country;
        
    
    // ========= Constructors ==================================

    public Address() {}

    public Address(String street, String city, String state, String zipcode, Country country) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }
    
    // ========= Getters and Setters ======================================
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }
        
    // ============== overriden Object methods ==============================
    
    @Override
    public String toString() {
        
        String className = getClass().getSimpleName() + " ";
        
        if(street != null && !street.trim().isEmpty())
            className += "street: " + street;
        if(city != null && !city.trim().isEmpty())
            className += "city: " + city;
        if(state != null && !state.trim().isEmpty())
            className += "state: " + state;
        if(zipcode != null && !zipcode.trim().isEmpty())
            className += "zipcode: " + zipcode;
        
        return className;
        
    }
    
    
    
}
