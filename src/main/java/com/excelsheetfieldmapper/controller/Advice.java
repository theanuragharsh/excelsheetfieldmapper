package com.excelsheetfieldmapper.controller;

import com.excelsheetfieldmapper.exception.EmptyFileException;
import com.excelsheetfieldmapper.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class Advice extends RuntimeException {

    @ExceptionHandler(EmptyFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HashMap<String, ErrorResponse>> emptyFileException(EmptyFileException emptyFileException) {
        log.error("EmptyFileException occured : {}", emptyFileException.getMessage());
        HashMap<String, ErrorResponse> errors = new HashMap<>();
        ErrorResponse apiErrorResponse = ErrorResponse.builder().category("API_ERROR").status(HttpStatus.NOT_FOUND)
                .message(emptyFileException.getMessage()).timestamp(LocalDateTime.now()).build();
        errors.put("ERRORS", apiErrorResponse);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
