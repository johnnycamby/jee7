/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xplicit.exceptions;

/**
 *
 * @author johnny
 */
public class ValidationException extends RuntimeException{
    
   public ValidationException(){
   
       super();
   }
   
   public ValidationException(String message){
   
       super(message);
   }
}
