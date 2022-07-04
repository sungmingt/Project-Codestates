package com.codestates.exception;

import lombok.Getter;


public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_ALREADY_EXISTS(406, "Member Already Exists"),
    COFFEE_NOT_ENOUGH(404, "Coffee Not Enough"),
    COFFEE_NOT_FOUND(404, "Coffee Not Found"),
    COFFEE_ALREADY_EXISTS(406, "Coffee Already Exists");

    @Getter
    private int status;
    @Getter
    private String message;


    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
