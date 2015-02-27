/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.util;

import com.xplicit.model.Category;
import com.xplicit.service.AbstractService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author johnny
 */
@Stateless
@LocalBean
@Loggable
public class CategoryService extends AbstractService<Category> implements Serializable{

    
    // ===================== Constructors ====================================
    
    public CategoryService() { 
    
        super(Category.class);
    }
    
    @Override
    protected Predicate[] getSearchPredicates(Root<Category> root, Category example) {
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        List<Predicate> predicatesList = new ArrayList<>();
        
        String name = example.getName();
        
        if (name != null && !"".equals(name)) {
            
            predicatesList.add(cb.like(cb.lower(root.<String> get("name")), '%' + name.toLowerCase() + '%'));
        }
        
        String description = example.getDescription();
        
        if (description != null && !"".equals(description)) {
            
            predicatesList.add(cb.like(cb.lower(root.<String> get("description")), '%' + description.toLowerCase() + '%'));
        }
        
        return predicatesList.toArray(new Predicate[predicatesList.size()]);
        
    }
    
}
