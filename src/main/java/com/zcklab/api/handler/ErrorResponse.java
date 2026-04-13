package com.zcklab.api.handler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// The Global Exception Handler will indicate all parameters that have errors, so we will list them here.
@Getter
public class ErrorResponse {

    private final List<Error> errors; //create a List named errors from the class Error

    public ErrorResponse(){ //constructor to create a new ArrayList for the list errors
        this.errors = new ArrayList<>();
    }


    // The addError method is used to add an object of the Error class to the errors list
    // (this might have been a bit confusing)
    public void addError(Error error){
        this.errors.add(error);
    }
}
