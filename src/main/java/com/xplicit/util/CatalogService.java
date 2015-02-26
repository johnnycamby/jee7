/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.util;

import com.xplicit.model.Category;
import com.xplicit.model.Product;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author johnny
 */
@Stateless
@Loggable
public class CatalogService implements Serializable{
    
    // ========================= Attributes ===============================
    
    @Inject
    private EntityManager em;
    
    public Category findCategory(@NotNull Long categoryId){
    
        return em.find(Category.class, categoryId);
    }
    
    public Category findCategory(@NotNull String categoryName){
    
        TypedQuery<Category> typedQuery = em.createNamedQuery(Category.FIND_BY_NAME, Category.class);
        typedQuery.setParameter("pname", categoryName);
        
        return typedQuery.getSingleResult();
    }
    
    public List<Category> findAllCategories(){
    
        TypedQuery<Category> typedQuery = em.createNamedQuery(Category.FIND_ALL, Category.class);
        return typedQuery.getResultList();
    }
    
    public Category createCategory(@NotNull Category category){
    
        em.persist(category);
        return category;
    }
    
    public Category updateCategory(@NotNull Category category){
    
        return em.merge(category);
    }
    
    public void removeCategory(@NotNull Category category){
    
        em.remove(em.merge(category));
    }
    
    public void removeCategory(@NotNull Long categoryId){
    
        removeCategory(findCategory(categoryId));
    }
    
    public List<Product> findProducts(@NotNull String categoryName){
    
        TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_BY_CATEGORY_NAME, Product.class);
        typedQuery.setParameter("pname", categoryName);
        return typedQuery.getResultList();
    }
    
    public Product findPoduct(@NotNull Long productId){
    
        Product product = em.find(Product.class, productId);
        return product;
    }
    
    public List<Product> findAllProducts(){
    
        TypedQuery<Product> typedQuery = em.createNamedQuery(Product.FIND_ALL, Product.class);
        return typedQuery.getResultList();
    }
    
    public Product createProduct(@NotNull Product product){
    
        if(product.getCategory() != null && product.getCategory().getId() == null)
            em.persist(product.getCategory());
        em.persist(product);
        
        return product;
    }
    
    
    
    
}
