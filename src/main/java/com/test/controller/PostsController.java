package com.test.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.Dto.GetAllResponseDto;
import com.test.Dto.PostsRequestDto;
import com.test.Dto.PwcheckRequestDto;
import com.test.entity.Posts;
import com.test.repository.PostsRepository;
import com.test.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsRepository postsRepository;

    private final PostsService postsService;


    // 2. 전체 읽기 클리어

    @GetMapping("/api/posts")
    public List<GetAllResponseDto> getPost(){

        return postsService.getall();
    }

    @PostMapping("/api/posts")
    public Posts Posting(@RequestBody PostsRequestDto requestDto){
        Posts posts = new Posts(requestDto);
        return postsRepository.save(posts);
    }






//     3. 게시글 작성 클리어
//     제목, 작성자명, 비밀번호, 작성 내용
//    @PostMapping("/api/posts")
//    public Posts createPost(@RequestBody PostsRequestDto requestDto){
//        Posts posts = new Posts(requestDto);
//        postsRepository.save(posts);
//        return posts;
//    }



    // id 조회해서 읽기
    // 제목 작성자명 작성날짜 작성 내용 조회
    @GetMapping("/api/posts/{id}")
    public Posts getPost(@PathVariable Long id){
        return postsService.search(id);
    }



   //  게시글 삭제
   @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postsRepository.deleteById(id);
        return id;
   }

// pw 체크
    @PostMapping("/api/posts/{id}")
    public boolean passwordcheck(@PathVariable Long id, @RequestBody PwcheckRequestDto pwcheckRequestDto) {
      return postsService.checkpw(id,pwcheckRequestDto);

    }

    // 게시글 수정
    // 제목, 작성자명, 비밀번호, 작성 내용

    @PutMapping("/api/posts/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        postsService.update(id, requestDto);
        return id;
    }


}
