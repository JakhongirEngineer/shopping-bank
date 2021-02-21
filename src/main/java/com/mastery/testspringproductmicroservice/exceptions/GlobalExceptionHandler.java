package com.mastery.testspringproductmicroservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<Object>(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FailedToSaveException.class})
    public ResponseEntity<Object> handleFailedToSaveException(FailedToSaveException e){
        return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    public ResponseEntity<Object> handleServerErrorException(ServerErrorException e){
        return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
