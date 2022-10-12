package com.test.Dto;

import com.test.entity.Posts;
import com.test.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor

public class GetAllResponseDto {



    private Long id;

    private String title;

    private String content; // content

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public GetAllResponseDto(Posts posts){

        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
        this.createdAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();

    }


//          List<GetAllResponseDto> getallResponseDtoList = new ArrayList<>();
}
