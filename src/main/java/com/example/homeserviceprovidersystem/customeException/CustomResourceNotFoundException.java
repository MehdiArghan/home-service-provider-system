package com.example.homeserviceprovidersystem.customeException;

public class CustomResourceNotFoundException extends CustomRuntimeException{
    public CustomResourceNotFoundException(String message) {
        super(message);
    }
}
