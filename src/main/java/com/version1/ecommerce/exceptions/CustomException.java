package com.version1.ecommerce.exceptions;

public class CustomException extends IllegalArgumentException{
    public CustomException(String msg){
        super(msg);
    }
}
