package com.example.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링 부트의 자동 설정, 스프링 Bean 일기와 생성을 모두 자동으로 설정, 가장 먼저 처리하기 때문에 항상 최상단에 위치
@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        // 내장 WAS를 실행
        SpringApplication.run(BookApplication.class, args);
    }

}
