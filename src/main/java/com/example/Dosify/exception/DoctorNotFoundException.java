package com.example.Dosify.exception;

import com.example.Dosify.transformer.DoctorTransformer;

import javax.print.Doc;

public class DoctorNotFoundException extends Exception{

    public DoctorNotFoundException(String message){
        super(message);
    }
}
