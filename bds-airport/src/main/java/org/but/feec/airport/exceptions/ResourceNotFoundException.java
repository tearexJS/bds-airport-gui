package org.but.feec.airport.exceptions;

/**
 * ResourceNotFoundException
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
    
}