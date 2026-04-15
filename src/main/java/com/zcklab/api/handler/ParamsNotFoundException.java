package com.zcklab.api.handler;

public class ParamsNotFoundException extends RuntimeException{
    public ParamsNotFoundException(String message) {
        super(message);
    }
}
