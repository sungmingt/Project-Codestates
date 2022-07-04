package com.codestates.coffee.domain;

import com.codestates.coffee.dto.CoffeePatchDTO;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
public class Coffee {

    @Id @GeneratedValue
    @Column(name = "coffee_id")
    private Long coffeeId;

    @Column(name = "eng_name")
    private String engName;

    @Column(name = "kor_name")
    private String korName;

    private int price;

    private int stockQuantity;

    public Coffee(String engName, String korName, int price, int stockQuantity) {
        this.engName = engName;
        this.korName = korName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public Coffee(){}   //기본생성자 없으면 단일조회에서 오류가남. 왜?

    //==============로직===================

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new BusinessLogicException(ExceptionCode.COFFEE_NOT_ENOUGH);
        }
        this.stockQuantity -= quantity;
    }


    //======update 로직=========
    public Coffee update(CoffeePatchDTO dto) {
        this.engName = dto.getEngName();
        this.korName = dto.getKorName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
        return this;
    }
}
