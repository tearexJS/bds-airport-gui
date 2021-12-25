package org.but.feec.airport.exceptions;


public class DataAccesException extends RuntimeException{
    public DataAccesException(String message){
        super(message);
    }
    public DataAccesException(Throwable cause){
        super(cause);
    }
}
