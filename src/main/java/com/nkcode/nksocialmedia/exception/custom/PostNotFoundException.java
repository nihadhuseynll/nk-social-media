package com.nkcode.nksocialmedia.exception.custom;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message){
        super(message);
    }
}
