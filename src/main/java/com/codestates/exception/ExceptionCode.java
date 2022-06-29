package com.codestates.exception;

import lombok.Getter;


public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_ALREADY_EXISTS(406, "Member Already Exists");

    @Getter
    private int status;
    @Getter
    private String message;


    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
