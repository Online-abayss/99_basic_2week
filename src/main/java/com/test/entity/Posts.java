package com.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.Dto.PostsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends Timestamped {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
//    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String author;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

//    @JsonIgnore
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;



//    @Column(nullable = false)
//    private LocalDateTime modifiedtime;

//    public Posts(String username, String password, String post){
//        this.username = username;
//        this.password = password;
//
//    }

    public Posts(PostsRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();

    }


    public void update(PostsRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();

    }


//    public Posts(PostssRequest_testDto postssRequest_testDto){
//        this.title = postssRequest_testDto.getTitle();
//        this.username = postssRequest_testDto.getUsername();
//        this.post = postssRequest_testDto.getPost();
//    }



}
