package com.test.Dto;

import com.test.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private String author;
    private String content;
//    private Long id;

    public CommentResponseDto(Comment comment){
//        this.id = getId();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
    }
}
