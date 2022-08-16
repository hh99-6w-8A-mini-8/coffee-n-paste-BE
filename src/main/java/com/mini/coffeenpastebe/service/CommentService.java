package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.comment.Comment;
import com.mini.coffeenpastebe.domain.comment.dto.CommentListResponseDto;
import com.mini.coffeenpastebe.domain.comment.dto.CommentRequestDto;
import com.mini.coffeenpastebe.domain.comment.dto.CommentResponseDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.post.Post;
import com.mini.coffeenpastebe.repository.CommentRepository;
import com.mini.coffeenpastebe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    // Comment List 조회
    public List<CommentListResponseDto> getComment(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        List<Comment> comments = commentRepository.findByPost_Id(post.getId());
        List<CommentListResponseDto> commentListResponseDto = new ArrayList<>();

        for (Comment comment : comments) {
            commentListResponseDto.add(
                    CommentListResponseDto.builder()
                            .postId(postId)
                            .comments(commentRepository.findByPost_Id(comment.getPost().getId()).stream().map(CommentResponseDto::new).collect(Collectors.toList()))
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
                .memberNickname(comment.getMember().getMemberNickname())
                .commentContent(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();

        return commentResponseDto;
    }

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, Member member) {
        // 게시글 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 게시글 입니다. postId: " + postId);
                });

        // 댓글 생성
        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content(requestDto.getCommentContent())
                .build();

        // 저장
        commentRepository.save(comment);

        // commentResponseDto로 리턴
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .commentContent(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public void removeComment(Long commentId, Member member) {
        // 댓글이 존재하는지 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다. commentId: " + commentId);
        });

        // 댓글 작성자와 로그인한 사용자가 일치하는지 확인
        if (!comment.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("댓글 작성자만 삭제할 수 있습니다.");
        }

        // 삭제
        commentRepository.delete(comment);
    }
}
