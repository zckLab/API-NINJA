package com.zcklab.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //@ExceptionHandler is precisely the ideal key for our problem, because with this annotation we transform our generic
    // error (which shows all our logic) into an error with HTTP Status and an error message.
    //(MethodArgumentNotValidException.class) is the type of error we will handle
    @ExceptionHandler(MethodArgumentNotValidException.class)

    //As seen before, we created a public ResponseEntity, however this time we added a <?>,
    // because we will receive various types of attributes, string, email, localdata, etc,
    // so it needs to be of type Generic, otherwise we would have to create a method
    // for each type (which is annoying asf)
    public ResponseEntity<?> validateException(MethodArgumentNotValidException e) { //variable and of type MethodArgumentNotValidException

        ErrorResponse response = new ErrorResponse(); // calling constructor

        e.getAllErrors().forEach(item -> { // e getAllErrors and item iterates through each one

        // FieldError, for those who don't know, refers to request errors according to the rules of our DTO

            String field = ((FieldError) item).getField(); // field = item(FieldError type).getField

            String message = item.getDefaultMessage(); // self-explanatory, item picks up the default message

            response.addError(new Error(field, message)); // We call the addError method with the parameters created above
        });


        // So, we return the HTTP status of BAD_REQUEST (at least for this specific type of error)
        // and put our response variable, which includes all the other parameters, in the response body
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(NinjaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNinjaNotFound(NinjaNotFoundException e) {
        ErrorResponse error = new ErrorResponse();

        error.addError(new Error("ninja_list", e.getMessage())); //FROM THE LINE throw new NinjaNotFoundException("Ninja not found"); on service

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
