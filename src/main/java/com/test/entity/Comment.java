package com.test.entity;



import com.test.Dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    public Comment(String author, String content){
        this.author = author;
        this.content = content;
    }


    public Comment(CommentRequestDto commentRequestDto) {
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }
    public void update(CommentRequestDto commentRequestDto){
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }
}
