package com.formacion.BS7_2.exception;

import lombok.Data;


import java.util.Date;
@Data
public class CustomError {
    private String message;


    private int httpCode;
    private Date timestamp;

    public CustomError(String message, int httpCode) {
        this.message= message;
        this.httpCode= httpCode;
        this.timestamp= new Date();
    }
}
