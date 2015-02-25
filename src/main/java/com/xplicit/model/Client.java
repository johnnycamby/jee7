/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.model;

import com.xplicit.constraints.Email;
import com.xplicit.constraints.Login;
import com.xplicit.exceptions.ValidationException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import sun.misc.BASE64Encoder;

/**
 *
 * @author johnny
 */
@Entity
@NamedQueries({

              @NamedQuery(name = Client.FIND_BY_LOGIN, query = "SELECT c FROM Client WHERE c.login = :login"),
              @NamedQuery(name = Client.FIND_BY_LOGIN_PASSWORD, query = "SELECT c FROM Client WHERE c.login = :login AND c.password = :password"),
              @NamedQuery(name = Client.FIND_ALL, query = "SELECT c FROM Client c")
                          
             })
@XmlRootElement
public class Client implements Serializable{
    
    // ============================= Constants ==========================
    
    public static final String FIND_BY_LOGIN = "Client.findByLogin";
    public static final String FIND_BY_LOGIN_PASSWORD = "Client.findByLoginAndPassword";
    public static final String FIND_ALL = "Client.findAll";
    
    
    // ============================ Attributes ==========================
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Version
    @Column(name = "version")
    private int version;
    
    @Column(length = 50,name = "first_name", nullable = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;
    
    @Column(length = 50, name ="last_name", nullable = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;
    
    @Column
    private String telephone;
    
    @Column
    @Email
    private String email;
   
    @Column(length = 10, nullable = false)
    @Login
    private String login;
    
    @Column(length = 256, nullable = false)
    @NotNull
    @Size(min = 1, max = 256)
    private String password;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @Past
    private Date dateOfBirth;
    
    @Transient
    private Integer age;
    
    @Embedded
    @Valid
    private Address homeAddress = new Address();
    
    // =========================== Constructors =========================
    public Client() {}

    public Client(String firstName, String lastName,  String email, String login, String plainPassword, Date dateOfBirth, Address address) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = digestPassword(plainPassword);
        this.dateOfBirth = new Date();
        this.homeAddress = address;
    }
    
    // ============================ Getter and Setters ======================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion( final int version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    
    // ======================== Operational Methods (Business Methods) ========

    /**
     * Password to be digested with SHA-256 code then encoded with Base64
     * @param plainPassword :: password to be digested and encoded
     * @return digested password
     */
    public String digestPassword(String plainPassword) {
      
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainPassword.getBytes("UTF-8"));
            
            byte [] pwdDigest = md.digest();
            
            return new  BASE64Encoder().encode(pwdDigest);
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            
            throw new RuntimeException("Exception encoding password", e);
        }
    }
    
    /**
     *  It l'l check if a given password matches the user
     * @param pwd :: password 
     * @throws ValidationException :: For any password mismatch or invalidity
     */
    public void matchPassword(String pwd){
    
        if (pwd == null || "".equals(pwd)) 
            throw new ValidationException("Invalid password");
            
            String digestPWD = digestPassword(pwd);
            
            if(!digestPWD.equals(password))
                throw new ValidationException("Passwords don't match");
        
    }
    
    /**
     * - It's used the Life-cycle events
     * - Computes Client age 
     * - 
     */
    @PostLoad
    @PostPersist
    @PostUpdate
    public void computeAge()
    {
        if (dateOfBirth == null) {
            age = null;
        }
        
        Calendar calPast = new GregorianCalendar();
        calPast.setTime(dateOfBirth);
        
        Calendar calNow = new GregorianCalendar();
        calNow.setTime(new Date());
        
        int adjust = 0;
        
        if (calNow.get(Calendar.DAY_OF_YEAR) - calPast.get(Calendar.DAY_OF_YEAR) < 0) {
            
            adjust = -1;
        }
        
        age = calNow.get(Calendar.YEAR) - calPast.get(Calendar.YEAR) + adjust;
    
    }
    
    
    // =================== Object overriden methods ==========================
    
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
        if (!(obj instanceof Client)) {
            return false;
        }
        
        Client other = (Client)obj;
        
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
                .append(" : login : ")
                .append(login)
                .append(" : firstName : ")
                .append(firstName)
                .append(" : lastName : ")
                .append(lastName)
                .append(" : email : ")
                .append(email)
                .append(" : dateOfBirth : ")
                .append(dateOfBirth)
                .append(" : age : ")
                .append(age);
        
        return sb.toString();
        
    }
    
    
    
    
}
