package com.example.book.repository;

import com.example.book.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// MyBatis 에서 Dao라고 불리는 DB layer 접근자, JPA에선 Repository라고 부름
// JpaRepository<Entity 클래스, PK타입> 을 상속하면 기본적인 CRUD 메소드가 자동으로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();
}
