/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 */
@Embeddable
public class CreditCard implements Serializable{
    
    // ====================== Attributes ==================================
    
    @Column(length = 30, name = "credit_card_number", nullable = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String creditCardNumber;
    
    @Enumerated
    @Column(name = "credit_card_type")
    @NotNull
    private String creditCardType;
    
    @Column(length = 5, name = "credit_card_expiry_date", nullable = false)
    @NotNull
    @Size(min = 1, max = 5)
    private String creditCardExpDate;
    
    // ===================== Constructors ================================
    
    public CreditCard() { }

    public CreditCard(String creditCardNumber, String creditCardType, String creditCardExpDate) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardType = creditCardType;
        this.creditCardExpDate = creditCardExpDate;
    }
    
    // ================== Setter and Getters =============================
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardExpDate() {
        return creditCardExpDate;
    }

    public void setCreditCardExpDate(String creditCardExpDate) {
        this.creditCardExpDate = creditCardExpDate;
    }
    
    // ======================== Object overriden methods =================
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
                sb.append(getClass().getSimpleName()).append(" ")
                .append(" : creditCardNumber : ")
                .append(creditCardNumber)
                .append(" : creditCardType : ")
                .append(creditCardType)
                .append(" : creditCardExpDate : ")
                .append(creditCardExpDate);
                
                return sb.toString();
    }
    
    
    
    
    
}
