package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.comment.Comment;
import com.mini.coffeenpastebe.domain.comment.dto.CommentListResponseDto;
import com.mini.coffeenpastebe.domain.comment.dto.CommentRequestDto;
import com.mini.coffeenpastebe.domain.comment.dto.CommentResponseDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.repository.CommentRepository;
import com.mini.coffeenpastebe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // Comment List 조회
    public List<CommentListResponseDto> getComment(Long postId) {
        List<Comment> comments = commentRepository.findByPost_PostId(postId);
        if (null == comments) {
            throw new IllegalArgumentException("등록되지 않은 Comment 입니다.");
        }
        List<CommentListResponseDto> commentListResponseDto = new ArrayList<>();

        for (Comment comment : comments) {
            commentListResponseDto.add(
                    CommentListResponseDto.builder()
                            .postId(postId)
                            .comments(commentRepository.findByPost_PostId(comment.getPost().getId()).stream().map(CommentResponseDto::new).collect(Collectors.toList()))
                            .build()
            );
        }
        return commentListResponseDto;

    }

    // Comment 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("Comment 가 존재하지 않습니다.");
                });
        if (!comment.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        comment.update(commentRequestDto);

        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .commentId(comment.getId())
                .memberName(comment.getMember().getMemberName())
                .commentContent(comment.getContent())
                .build();

        return commentResponseDto;
    }
}
