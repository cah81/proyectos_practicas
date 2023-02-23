package com.formacion.BS7_2.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data

public class EntityNotFoundException extends RuntimeException{
    Date timeStamp;
    private int httpCode;

    public EntityNotFoundException(String message, int httpCode, Date timeStamp ){
        super(message);
        this.httpCode = httpCode;
        this.timeStamp = timeStamp;


    }
}
