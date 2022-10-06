package com.example.onlineschool.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEnrolmentsException extends RuntimeException{

    public NoEnrolmentsException(String msg){

        super(msg);
    }
}
