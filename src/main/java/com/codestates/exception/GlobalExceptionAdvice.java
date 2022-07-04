package com.codestates.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ErrorResponse handleException(MethodArgumentNotValidException e) {

        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        return response;
    }

    @ExceptionHandler
    public ErrorResponse handleConstraintViolationException(
            ConstraintViolationException e) {

        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());
        return response;
    }

    @ExceptionHandler  // exception + ErrorResponse 수정본 정상작동 확인
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        log.info("message ={}", e.getMessage());

        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }



}
