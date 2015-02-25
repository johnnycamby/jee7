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
import javax.validation.constraints.DecimalMin;

/**
 *
 * @author johnny
 */
@Constraint(validatedBy = {})
@DecimalMin("10")
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR
       })
@Documented
public @interface Price {
    
    // Attributes
    String message() default "{com.xplicit.constraints.Price.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    // Inner Annotation
   @Retention(RetentionPolicy.RUNTIME)
   @Target({
       ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,ElementType.TYPE, ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR
   })
    public @interface List
    {
        Price[] value();
    }
}
