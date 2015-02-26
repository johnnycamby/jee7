/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.Valid;

/**
 *
 * @author johnny
 */
@Entity
@Table(name = "purchase_order")
@NamedQueries({
             @NamedQuery(name = PurchaseOrder.FIND_ALL, query = "SELECT po FROM PurchaseOrder po")
             })
public class PurchaseOrder implements Serializable{
 
    
    // =============== Constants =========================================
    
    public static final String FIND_ALL = "Order.findAll";
    
    // ============== Attributes =========================================
    
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
  
    @Version
   @Column(name = "version")
   private int version;

   @Column(name = "order_date", updatable = false)
   @Temporal(TemporalType.DATE)
   private Date orderDate;

   @Column
   private Float totalWithoutVat;

   @Column(name = "vat_rate")
   private Float vatRate;

   @Column
   private Float vat;

   @Column
   private Float totalWithVat;

   @Column(name = "discount_rate")
   private Float discountRate;

   @Column
   private Float discount;

   @Column
   private Float total;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "customer_fk", nullable = false)
   private Client client;

   @OneToMany//TODO (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(name = "t_order_order_line",
         joinColumns = {@JoinColumn(name = "order_fk")},
         inverseJoinColumns = {@JoinColumn(name = "order_line_fk")})
   private Set<OrderLine> orderLines = new HashSet<OrderLine>();

   @Embedded
   @Valid
   private Address deliveryAddress = new Address();

   @Embedded
   @Valid
   private CreditCard creditCard = new CreditCard();
   
   
   // =================== Constructors ==================================
   
   public PurchaseOrder(){}

   public PurchaseOrder(Client client, CreditCard creditCard, Address deliveryAddress)
   {
      this.client = client;
      this.creditCard = creditCard;
      this.deliveryAddress = deliveryAddress;
   }
   
   // =============== LifeCycle Methods =================================
   
   @PrePersist
   public void setDefaultData(){
   
       orderDate = new Date();
   }
   
   
   // ================ Getters and Setters ==============================
   
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

   public Date getOrderDate()
   {
      return orderDate;
   }

   public void setOrderDate(Date orderDate)
   {
      this.orderDate = orderDate;
   }

   public Float getTotalWithoutVat()
   {
      return totalWithoutVat;
   }

   public void setTotalWithoutVat(Float totalWithoutVat)
   {
      this.totalWithoutVat = totalWithoutVat;
   }

   public Float getVatRate()
   {
      return vatRate;
   }

   public void setVatRate(Float vatRate)
   {
      this.vatRate = vatRate;
   }

   public Float getVat()
   {
      return vat;
   }

   public void setVat(Float vat)
   {
      this.vat = vat;
   }

   public Float getTotalWithVat()
   {
      return totalWithVat;
   }

   public void setTotalWithVat(Float totalWithVat)
   {
      this.totalWithVat = totalWithVat;
   }

   public Float getDiscountRate()
   {
      return discountRate;
   }

   public void setDiscountRate(Float discountRate)
   {
      this.discountRate = discountRate;
   }

   public Float getDiscount()
   {
      return discount;
   }

   public void setDiscount(Float discount)
   {
      this.discount = discount;
   }

   public Float getTotal()
   {
      return total;
   }

   public void setTotal(Float total)
   {
      this.total = total;
   }

   public Client getCustomer()
   {
      return this.client;
   }

   public void setCustomer(final Client client)
   {
      this.client = client;
   }

   public Set<OrderLine> getOrderLines()
   {
      return this.orderLines;
   }

   public void setOrderLines(final Set<OrderLine> orderLines)
   {
      this.orderLines = orderLines;
   }

   public Address getDeliveryAddress()
   {
      return deliveryAddress;
   }

   public void setDeliveryAddress(Address deliveryAddress)
   {
      this.deliveryAddress = deliveryAddress;
   }

   public CreditCard getCreditCard()
   {
      return creditCard;
   }

   public void setCreditCard(CreditCard creditCard)
   {
      this.creditCard = creditCard;
   }
   
   // ============= Object overriden methods  =========================
   
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
        if (!(obj instanceof PurchaseOrder)) {
            return false;
        }
        
        PurchaseOrder other = (PurchaseOrder)obj;
        
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
                .append(" : orderDate : ")
                .append(orderDate)
                .append(" : totalWithoutVat : ")
                .append(totalWithoutVat)
                .append(" : vatRate : ")
                .append(vatRate)
                .append(" : vat : ")
                .append(vat)
                .append(" : totalWithVat : ")
                .append(totalWithVat)
                .append(" : discountRate : ")
                .append(discountRate)
                .append(" : discount : ")
                .append(discount)
                .append(" : total : ")
                .append(total);
        
        return sb.toString();
        
    }
}
