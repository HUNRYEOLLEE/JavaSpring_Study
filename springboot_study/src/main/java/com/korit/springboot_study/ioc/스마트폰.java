package com.korit.springboot_study.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class 스마트폰 {

    @Autowired          // <- 아래의 스마트폰 객체의 생성자를 생략가능
    private 배터리 b;

//    public 스마트폰(배터리 b) {
//        this.b = b;
//    }

    public void 전원켜기() {
        b.전류공급();
        System.out.println("배터리로부터 전류를 공급받아 전원을 켭니다.");
    }
}
