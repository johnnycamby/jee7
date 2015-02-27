/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.service;

import com.xplicit.util.Loggable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author johnny
 */
@Loggable
public abstract class AbstractService<T> {
    
    // ======================= Attributes =================================
    
    @PersistenceContext(unitName = "xplicitCarsPU")
    protected EntityManager em;
    
    private Class<T> entityClass;
    
    // ===================== Constructors =================================
    
    public AbstractService() {}

    public AbstractService(Class<T> entityClass) {
        
        this.entityClass = entityClass;
    }
    
    // =================== Business methods ==============================
    
    public T persist(T entity){
    
        em.persist(entity);
        return entity;
    }
    
    public T findById(Long id){
    
        return em.find(entityClass, id);
    }
    
    public void remove(T entity){
    
        em.remove(em.merge(entity));
    }
    
    // update method
    public T merge(T entity){
    
        return em.merge(entity);
    }
    
    public List<T> listAll(Integer startPosition, Integer maxResult){
    
        TypedQuery<T> findAllQuery = getListAllQuery();
        
        if(startPosition != null){
        
            findAllQuery.setFirstResult(startPosition);
        }
        
        if (maxResult != null) {
            
            findAllQuery.setMaxResults(maxResult);
        }
        
        final List<T> results = findAllQuery.getResultList();
        
        return results;
    }
    
    public List<T> listAll(){
    
        return getListAllQuery().getResultList();
    }
    
    public TypedQuery<T> getListAllQuery() {
        
        CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass);
        
        return em.createQuery(criteriaQuery.select(criteriaQuery.from(entityClass)));
    }
    
    public Long count(T example){
    
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<Long> cqCount = builder.createQuery(Long.class);
        Root<T> root = cqCount.from(entityClass);
        cqCount = cqCount.select(builder.count(root))
                .where(getSearchPredicates(root, example));
        
        long count = em.createQuery(cqCount).getSingleResult();
        
        return count;
        
    }
    
    public List<T> page(T example, int page, int pageSize){
    
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<T> cq = builder.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        TypedQuery<T> query = em.createQuery(cq.select(root)
                .where(getSearchPredicates(root, example)));
        
        query.setFirstResult(page * pageSize).setMaxResults(pageSize);
        
        List<T> pageItems = query.getResultList();
        
        return  pageItems;
    }

    protected abstract Predicate[] getSearchPredicates(Root<T> root, T example);
    
    
}
