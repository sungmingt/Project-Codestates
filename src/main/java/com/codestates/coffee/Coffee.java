package com.codestates.coffee;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Coffee {

    @Id @GeneratedValue
    private String coffeeId;

    @Column(name = "eng_name")
    private String engName;

    @Column(name = "kor_name")
    private String korName;

    private Long price;

    private Long quantity;



    public Coffee(String engName, String korName, Long price, Long quantity) {
        this.engName = engName;
        this.korName = korName;
        this.price = price;
        this.quantity = quantity;
    }
}
