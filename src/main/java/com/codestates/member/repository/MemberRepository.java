package com.codestates.member.repository;

import com.codestates.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    EntityManager em;

    public Member saveMember(Member member) {
        em.persist(member);
        return member;
    }

    public Member updateMember(Member member) {
        Member findmember = em.find(Member.class, member.getMemberId());
        Member newMember = new Member(findmember.getMemberId(),
                findmember.getName(),
                findmember.getEmail(),
                findmember.getPhone());

        em.persist(newMember);
        return newMember;
    }

    public Member findMember(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return member;
    }

    public List<Member> findMembers() {
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return members;
    }

    public void deleteMember(Long memberId) {
        em.remove(memberId);
    }


}
