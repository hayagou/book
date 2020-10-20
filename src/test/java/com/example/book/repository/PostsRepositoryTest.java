package com.example.book.repository;

import com.example.book.domain.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing // auditing 기능을 활성화 해야 날짜에 null이 아닌 값이 들어감
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // JUnit 단위 테스트가 끝날 때마다 수행되는 메소드
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void postSave(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        LocalDateTime now = LocalDateTime.of(2020,2,25,0,0,0);
        // 테이블 posts에 insert/update 쿼리를 실행, id 값이 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder().title(title).content(content).author("de0978@gmail.com").build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>> createDate : " + posts.getCreatedDate() + ",modifiedDate : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}