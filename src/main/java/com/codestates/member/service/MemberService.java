package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.Member;
import com.codestates.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Optional;

import static com.codestates.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public Member saveMember(Member member) {
        emailExists(member);
        phoneNumberExists(member);

        memberRepository.save(member);
        return member;
    }

    public Member updateMember(Member member){
        emailExists(member);
        phoneNumberExists(member);

        Member findMember = findVerifiedMember(member.getMemberId());
        findMember.update(member);  //변경 감지

        return findMember;  //update된 member 리턴
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return findVerifiedMember(memberId);
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Long deleteMember(Long memberId) {
        findVerifiedMember(memberId);

        memberRepository.deleteById(memberId);
        return memberId;
    }



    //============validation methods==============


    private Member findVerifiedMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.orElseThrow(() ->
                new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    private void emailExists(Member member) {  //이메일 중복여부
        String email = member.getEmail();

        Optional<Member> findMembers = memberRepository.findByEmail(email);
        if (findMembers.isPresent()){
            throw new BusinessLogicException(MEMBER_ALREADY_EXISTS);
        }
    }

    private void phoneNumberExists(Member member) {  //전화번호 중복여부
        String phone = member.getPhone();

        Optional<Member> findMembers = memberRepository.findByPhone(phone);
        if (findMembers.isPresent()){
            throw new BusinessLogicException(MEMBER_ALREADY_EXISTS);
        }
    }

}
