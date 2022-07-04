package com.codestates.member.repository;

import com.codestates.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {  //Spring Data JPA 적용


    Optional<Member> findByEmail(String email);    ////
    Optional<Member> findByPhone(String phone);   ////

}
