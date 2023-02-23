package com.formacion.bs13_2.exception;

import lombok.Data;

import java.util.Date;

@Data
public class UnprocessableEntityException extends RuntimeException{
    Date timeStamp;
    private int httpCode;

    public UnprocessableEntityException(String message, int httpCode, Date timeStamp) {
        super(message);
        this.httpCode = httpCode;
        this.timeStamp = timeStamp;


    }
}