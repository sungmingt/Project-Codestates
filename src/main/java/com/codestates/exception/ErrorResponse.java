package com.codestates.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

//    private int status;
//    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;
    private BusinessError businessError;

//======================Constructor======================

    private ErrorResponse(final List<FieldError> fieldErrors,
                          final List<ConstraintViolationError> violationErrors, BusinessError businessError) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.businessError = businessError;
    }

//    private ErrorResponse(int status, String message) {
//        this.status = status;
//        this.message = message;
//    }


//=====================of================================


    // (4) BindingResult에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null, null);
    }

    // (5) Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations), null);
    }

    public static ErrorResponse of(BusinessLogicException e) {
        return new ErrorResponse(null, null, BusinessError.of(e));
    }


//=======================inner class===================================


    @Getter
    public static class FieldError {
        private final String field;
        private final Object rejectedValue;
        private final String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                    "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                            .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError{
        private final String propertyPath;
        private final Object rejectedValue;
        private final String reason;

        public ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations
        ) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class BusinessError {

        private final int status;
        private final String message;

        public BusinessError(ExceptionCode exceptionCode) {
            this.status = exceptionCode.getStatus();
            this.message = exceptionCode.getMessage();
        }

        public static BusinessError of(BusinessLogicException businessLogicException) {
            return new BusinessError(businessLogicException.getExceptionCode());
        }
    }
}
