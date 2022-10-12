package com.test.controller;



import com.test.Dto.CommentRequestDto;
import com.test.Dto.CommentResponseDto;
import com.test.entity.Comment;

import com.test.repository.CommentRepository;
import com.test.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    private final CommentService commentService;

    // 댓글 전체 조회
    @GetMapping("/api/reply")
    public List<CommentResponseDto> getcommentall(){

        return commentService.getcommentall();
    }
    // 댓글 작성
    @PostMapping("/api/reply")
    public Comment reply(@RequestBody CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        return commentRepository.save(comment);

    }
    // 특정 댓글 수정
    @PutMapping("api/reply/{id}")
    public void updatereply(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto){
        commentService.updatecomment(id,commentRequestDto);
    }
    // 특정 댓글 조회
    @GetMapping("api/reply/{id}")
    public Comment commentsearch(@PathVariable Long id){
        return commentService.commentsearch(id);

    }
    // 댓글 삭제
    @DeleteMapping("api/reply/{id}")
    public void commentdelete(@PathVariable Long id){
        commentRepository.deleteById(id);
    }



}
