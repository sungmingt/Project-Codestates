package com.codestates.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long memberId;

    private String name;

    private String email;

    private String phone;
}
