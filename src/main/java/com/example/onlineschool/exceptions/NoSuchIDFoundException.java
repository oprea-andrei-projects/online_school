package com.example.onlineschool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchIDFoundException extends RuntimeException{

    public NoSuchIDFoundException(String msg){

        super(msg);
    }
}
