package com.examejpa.examenjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){

    }
}
