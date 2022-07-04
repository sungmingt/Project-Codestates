package com.codestates.member;

import com.codestates.member.dto.MemberPatchDto;
import com.codestates.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_phone")
    private String phone;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "member")
    public List<Order> orders = new ArrayList<>();


    public Member(Long memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    protected Member() {}

    //===============멤버 수정 로직==============

    public void update(Member member) {
        this.name = member.getName();
        this.email =member.getEmail();
        this.phone =member.getPhone();
    }
}
