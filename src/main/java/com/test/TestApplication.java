package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 졸라 중요함
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}

// 복붙재료. : 내 입문 숙제 자료 + 항해 강의 되는 원본 자료 + https://github.com/dds1q/HangHae_assignment_week04/blob/main/src/main/java/com/sparta/hanghae_assignment_week04/service/AuthService.java
// + https://github.com/COVER-SJ/Article -< 이거가 내가 원하던 방향성... 회원 가입 및 로그인 및 로그아웃만 될거임. 나머지는 postman , h2로 확인해봐야함