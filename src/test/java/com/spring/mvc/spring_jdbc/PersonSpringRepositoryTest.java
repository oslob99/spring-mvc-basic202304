package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {

    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest(){
        // given
        Person p = new Person();
        p.setPersonName("김스프");
        p.setPersonAge(2);
        // when
        repository.savePerson(p);
        // then
    }

    @Test
    void removePersonTest(){
        // given
        long id = 2L;
        // when
        repository.removePerson(id);
    }

    @Test
    void updatePersonTest(){
        Person person = new Person();
        person.setPersonName("신형만");
        person.setPersonAge(44);
        person.setId(1L);

        boolean flag = repository.updatePerson(person);

        assertTrue(flag);
    }

    @Test
    void findAllTest(){
        List<Person> people = repository.findAll();
        for (Person person : people) {
            System.out.println("person = " + person);

        }
    }

    @Test
    void findOneTest(){
        long id = 3L;
        Person person = repository.findOne(id);
        System.out.println("person = " + person);
    }

}