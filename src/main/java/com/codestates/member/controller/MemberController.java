package com.codestates.member.controller;

import com.codestates.member.Member;
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

@Controller
@RequestMapping(value = "/v1/members")
@Validated
public class MemberController {

    private MemberService memberService;
    private MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }


    @PostMapping  //멤버 생성
    public ResponseEntity postMember(@Validated @RequestBody MemberPostDto memberPostDto) {

        Member member = mapper.memberPostDtoToMember(memberPostDto);
        memberService.saveMember(member);

        return new ResponseEntity(mapper.memberToMemberResponseDto(member), HttpStatus.CREATED);
    }

    @PatchMapping("/{memberId}")  //멤버 수정
    public ResponseEntity patchMember(@PathVariable @Min(1) long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto) {

        Member member = mapper.memberPatchDtoToMember(memberPatchDto);
        memberService.updateMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }

    @GetMapping("/{member-id}") //멤버 검색
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(member),
                HttpStatus.OK);
    }

    @GetMapping  //전체 멤버 검색
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findMembers();

        List<MemberResponseDto> response = members.stream()
                .map(member -> mapper.memberToMemberResponseDto(member))
                .collect(Collectors.toList());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")  //멤버 삭제
    public ResponseEntity deleteMember(@PathVariable @Positive Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
