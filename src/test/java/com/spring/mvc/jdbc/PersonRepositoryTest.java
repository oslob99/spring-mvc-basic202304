package com.spring.mvc.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    @DisplayName("사람의 이름과 나이 정보를 DB person table에 삽입하기")
    void saveTest(){
        // given
        Person p = new Person();
        p.setPersonName("맹구");
        p.setPersonAge(16);
        // when
        repository.save(p);
        //then
    }

    @Test
    void findAllTest(){
        List<Person> people = repository.findAll();
        for (Person person : people) {
            System.out.println("person = " + person);
        }
    }
}