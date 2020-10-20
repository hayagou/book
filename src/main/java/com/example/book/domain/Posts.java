package com.example.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스임을 나타냄, SalesManager.java -> sale_manager table
public class Posts extends BaseTimeEntity{

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, IDENTITY 옵션 = auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블 컬럼을 나타냄, 선언하지않아도 해당클래스 필드는 모두 컬럼, 옵션추가할때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}

//        ※참고※
//
//        Entity의 PK는 Long 타입의 Auto_increament를 추천합니다. 이유는 주민등록번호와 같이 비즈니스상 유니크 키나, 여러 키를 조합한 복합키로 PK를 잡을 경우
//
//        FK를 맺을 때 다른 테이블에서 복한키 전부를 갖고 있거나, 중간 테이블을 하나 더 둬야 하는 상황이 발생합니다.
//        인덱스에 좋은 영향을 끼치지 못합니다.
//        유니크한 조건이 변경될 경우 PK 전체를 수정해야 하는 일이 발생합니다.
//        따라서, 주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는 것을 추천합니다.
//
//        ※참고※
//
//        Entity 클래스에서는 절대 Setter 메소드를 만들지 않습니다. 이유는 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경 시 정말 복잡해지기 때문입니다.
//
//        대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 합니다.
//
//        Setter가 없는 상황에서 어떻게 값을 채워 DB에 삽입 해야할까???
//
//        기본적인 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입 하는 것 이며, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경 하는 것을 전제로 합니다.
//
//        또 다른 방법으로는 @Builder를 통해 제공되는 빌더 클래스 를 사용하는 것이다. 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 똑같습니다. 다만, 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 가 없습니다.