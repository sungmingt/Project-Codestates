package com.codestates.member.controller;

import com.codestates.member.Member;
import com.codestates.member.dto.MemberListDTO;
import com.codestates.member.service.MemberService;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.mapper.MemberMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/members")
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }


    @PostMapping  //멤버 생성
    public ResponseEntity postMember(@Validated @RequestBody MemberPostDto memberPostDto) {

        Member member = mapper.memberPostDtoToMember(memberPostDto);
        memberService.saveMember(member);

        MemberResponseDto response = mapper.memberToMemberResponseDto(member);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping  //멤버 수정
    public ResponseEntity patchMember(  //@PathVariable @Positive long memberId,  //id를 pathvariable로 받기 / 객체필드로 받기
                                      @RequestBody MemberPatchDto memberPatchDto) {

        Member member = mapper.memberPatchDtoToMember(memberPatchDto);
        memberService.updateMember(member);

        MemberResponseDto response = mapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}") //멤버 검색
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);

        MemberResponseDto response = mapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping  //전체 멤버 검색
    public ResponseEntity getMembers() {
        List<Member> findMembers = memberService.findMembers();

        List<MemberResponseDto> members = findMembers.stream()
                .map(mapper::memberToMemberResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity(new MemberListDTO(members), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")  //멤버 삭제
    public ResponseEntity deleteMember(@PathVariable @Positive Long memberId) {

        memberService.deleteMember(memberId);
        return new ResponseEntity(memberId, HttpStatus.NO_CONTENT);
    }
}
