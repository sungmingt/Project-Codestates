package com.codestates.coffee.repository;

import com.codestates.coffee.domain.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long>{  //Spring Data JPA 적용


    //    public List<Coffee> findCoffeeByName(String engName) {  // JPA에서 PK가 아닌 필드로 조회하는법??
//        List<Coffee> findCoffee =
//                em.createQuery("select c from Coffee c where c.eng_name = :eng_name", Coffee.class)
//                .setParameter("eng_name", engName)
//                        .getResultList();
//
//        return findCoffee;
//    }
    Optional<Coffee> findByEngName(String engName);

}

