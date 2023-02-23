package com.formacion.BS7_2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomError> hadlerEntytyNotFoutException(EntityNotFoundException e) {
        CustomError customError = new CustomError(e.getMessage(),e.getHttpCode());
        return ResponseEntity.status(404).body(customError);
    }
    @ExceptionHandler(value = {UnprocessableEntityException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException e) {
        CustomError customError = new CustomError(e.getMessage(), e.getHttpCode());
        return ResponseEntity.status(422).body(customError);

    }
}
