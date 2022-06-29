package com.codestates.member.dto;

import com.codestates.validator.NotSpace;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MemberPatchDto {

    private Long memberId;

    @NotSpace
    private String name;

    @Email
    private String email;

    @NotSpace
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;
}
