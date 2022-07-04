package com.codestates.member.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberListDTO<T> {
    private final T members;

    public MemberListDTO(T members) {
        this.members = members;
    }
}
