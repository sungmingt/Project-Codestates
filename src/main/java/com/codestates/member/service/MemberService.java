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

import static com.codestates.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public Member saveMember(Member member) {
        memberNameExists(member);

        memberRepository.saveMember(member);
        return member;
    }

    public Member updateMember(Member member){
        memberRepository.updateMember(member);
        return member;
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        Member member = memberRepository.findMember(memberId);
        if (member == null) {
            throw new BusinessLogicException(MEMBER_NOT_FOUND);
        }

        return member;
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findMembers();
    }

    public Long deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
        return memberId;
    }


    //============validation methods==============


    private void memberNameExists(Member member) {
        String name = member.getName();

        List<Member> members = memberRepository.findMembers();
        for (Member findMember : members) {
            if (findMember.getName().equals(member.getName())) {
                throw new BusinessLogicException(MEMBER_ALREADY_EXISTS);
            }
        }
    }

}
