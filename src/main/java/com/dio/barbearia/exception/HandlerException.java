package com.dio.barbearia.exception;

import java.time.LocalDateTime;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerException {

   @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<?> objectNotFunExException(ObjectNotFoundException err, HttpServletRequest request){
    EntityException ee = new EntityException(LocalDateTime.now(), err.getMessage(), HttpStatus.NO_CONTENT.value(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ee);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> illegalArgumentException(IllegalArgumentException err, HttpServletRequest request){
    EntityException ee = new EntityException(LocalDateTime.now(), err.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ee);
  }

}
