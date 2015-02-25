/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author johnny
 */
@Constraint(validatedBy = {})
@NotNull
@Size(min = 1, max = 10)
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR
         })
@Documented
public @interface Login {
        
    // Attributes 
    String message() default "{com.xplicit.constraints.Login.message}";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};
    
    // Inner Annotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target({
              ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR
            })
    public @interface List
    {
      Login [] value();
    }
        
}
