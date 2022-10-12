package com.test.service;

import com.test.Dto.CommentRequestDto;
import com.test.Dto.CommentResponseDto;
import com.test.entity.Comment;
import com.test.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public List<CommentResponseDto> getcommentall(){
        List<Comment> comments_lst = commentRepository.findAllByOrderByModifiedAtDesc();
        List<CommentResponseDto> commentResponseDtos_lst = new ArrayList<>();
        for(Comment comment : comments_lst){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtos_lst.add(commentResponseDto);

        }
        return commentResponseDtos_lst;

    }
    @Transactional
    public Long updatecomment(Long id, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("본인 댓글이 아닙니다.")
        );
        comment.update(commentRequestDto);
        return comment.getId();
    }
    @Transactional
    public Comment commentsearch(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("본인 댓글이 아닙니다.")
        );
        return comment;
    }





}
