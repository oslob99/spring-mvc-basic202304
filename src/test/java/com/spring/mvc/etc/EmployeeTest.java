package com.spring.mvc.etc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {


    @Test
    void tttt(){
        Employee e = Employee.builder()
                .position("대리")
                .empName("홍홍홍")
                .build();
        System.out.println("e = " + e);

        Actor actor = Actor.builder()
                .actorName("하하")
                .actorAge(12)
                .hasPhone(true)
                .build();
        System.out.println("actor = " + actor);

    }
}