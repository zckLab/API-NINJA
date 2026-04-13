package com.zcklab.api.handler;

public class NinjaNotFoundException extends RuntimeException{

    public NinjaNotFoundException(String message){
        super(message);
    }
}
