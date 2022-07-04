package com.codestates.coffee.service;

import com.codestates.coffee.domain.Coffee;
import com.codestates.coffee.dto.CoffeePatchDTO;
import com.codestates.coffee.repository.CoffeeRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;


    //커피 생성
    @Transactional
    public Coffee createCoffee(Coffee coffee) {
        ifExists(coffee);
        return coffeeRepository.save(coffee);
    }

    //커피정보 수정
    @Transactional
    public Coffee updateCoffee(CoffeePatchDTO dto) { ////setter 없이 update하는 방법??
        Coffee findCoffee = findVerifiedCoffee(dto.getCoffeeId());
        return findCoffee.update(dto);
        // 영속상태 -> save 안해도 됨?(o)
    }



    //단일 조회
    public Coffee findCoffee(Long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }

    //전체 조회
    public List<Coffee> findCoffees() {
        return coffeeRepository.findAll();
    }



    //============validation=================

    private void ifExists(Coffee coffee) {
        Optional<Coffee> findCoffee = coffeeRepository.findByEngName(coffee.getEngName());

        if (findCoffee.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COFFEE_ALREADY_EXISTS);
        }
    }

    private Coffee findVerifiedCoffee(Long coffeeId) {
        Optional<Coffee> coffee = coffeeRepository.findById(coffeeId);

        return coffee.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
    }

}



