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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author johnny
 *  
 */

/**
 * 
 * ======================================= ReadMe  ==============================================
 *  Constraint Annotation
 * =============================================================================================
 * - its retention policy contains RUNTIME
 * - The annotation must be annotated with javax.validation.Constraint (which refers to its list of constraint validation implementations)
 * - They are regular annotations, so they must define some meta-annotations.
 * 
 * ===============================================================================================
 * - @Target :: Specifies the target to which the annotation can be used.
 * - @Retention :: Specifies how the annotation will be operated. It is mandatory to use at least RUNTIME to allow the provider to inspect your objects at runtime.
 * - @Documented :: This optional meta-annotation specifies that this annotation will be included in the Javadoc or not.
 * - message: This attribute (which generally is defaulted to a key) provides the ability for a constraint to return an internationalized error message if the constraint is not valid.
 * - groups: Groups are typically used to control the order in which constraints are evaluated, or to perform partial validation.
 * - payload: This attribute is used to associate metadata information with a constraint.
 * ===============================================================================================
 * 
 */
@Constraint(validatedBy = {})
@Size(min = 5)
@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." 
 + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" 
 + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Documented
public @interface Email {
    
    // Attributes
    String message() default "{com.xplicit.constraints.Email.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
    
    // Inner Annotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
    public @interface List{
    
        Email[] value();
    }
}
