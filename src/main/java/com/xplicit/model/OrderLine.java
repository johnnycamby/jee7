/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "order_line")
public class OrderLine implements Serializable{
    
    // ================= Attributes ==================================
    
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   @Min(1)
   private Integer quantity;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "item_fk", nullable = false)
   private Item item;
   
   // ============= Constructors =====================================
   
   public OrderLine(){}

   public OrderLine(Integer quantity, Item item)
   {
      this.quantity = quantity;
      this.item = item;
   }
   
   // ================ Business methods ===============================
   
   public Float getSubTotal(){
   
       return item.getUnitCost() * quantity;
   }
   
   // ================ Getters and Setters =============================
   
   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   public Integer getQuantity()
   {
      return quantity;
   }

   public void setQuantity(Integer quantity)
   {
      this.quantity = quantity;
   }

   public Item getItem()
   {
      return this.item;
   }

   public void setItem(final Item item)
   {
      this.item = item;
   }
   
   // ================== Object overriden methods ======================
   
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
        if (!(obj instanceof OrderLine)) {
            return false;
        }
        
        OrderLine other = (OrderLine)obj;
        
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
                .append(" : quantity : ")
                .append(quantity);
        
        return sb.toString();
        
    }

}
