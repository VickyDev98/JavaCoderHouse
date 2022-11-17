package com.codarini.jpaservice.controller;


import com.codarini.jpaservice.service.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler({ResourceNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<String> resourceNotFoundException(Exception ex){

        return new ResponseEntity<>("EL PRODUCTO O CLIENTE NO EXISTE, O STOCK INSUFICIENTE", HttpStatus.NOT_FOUND);

    }

}
