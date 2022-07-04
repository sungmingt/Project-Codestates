package com.codestates.member.mapper;

import com.codestates.member.Member;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T22:20:00+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String phone = null;

        name = memberPostDto.getName();
        email = memberPostDto.getEmail();
        phone = memberPostDto.getPhone();

        Long memberId = null;

        Member member = new Member( memberId, name, email, phone );

        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Long memberId = null;
        String name = null;
        String email = null;
        String phone = null;

        memberId = memberPatchDto.getMemberId();
        name = memberPatchDto.getName();
        email = memberPatchDto.getEmail();
        phone = memberPatchDto.getPhone();

        Member member = new Member( memberId, name, email, phone );

        return member;
    }

    @Override
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setMemberId( member.getMemberId() );
        memberResponseDto.setName( member.getName() );
        memberResponseDto.setEmail( member.getEmail() );
        memberResponseDto.setPhone( member.getPhone() );

        return memberResponseDto;
    }
}
