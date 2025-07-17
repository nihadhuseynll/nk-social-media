package com.nkcode.nksocialmedia.exception.custom;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message){
        super(message);
    }
}
