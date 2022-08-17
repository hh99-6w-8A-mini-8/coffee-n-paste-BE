package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.UserDetailsImpl;
import com.mini.coffeenpastebe.domain.comment.dto.CommentListResponseDto;
import com.mini.coffeenpastebe.domain.comment.dto.CommentRequestDto;
import com.mini.coffeenpastebe.domain.comment.dto.CommentResponseDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Comment 조회
    @GetMapping("/api/comment")
    public ResponseEntity<?> getComment(@RequestParam("post-id") Long postId) {
        CommentListResponseDto commentResponseDto = commentService.getComment(postId);

        return ResponseEntity.ok()
                .body(commentResponseDto);
    }

    // Comment 수정
    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
                                           @RequestBody CommentRequestDto commentRequestDto,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        Member member = ((UserDetailsImpl) userDetails).getMember();
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto, member);

        return ResponseEntity.ok()
                .body(commentResponseDto);
    }

    // 댓글 등록
    @PostMapping("/api/comment")
    public ResponseEntity<?> createComment(
            @RequestParam("post-id") Long postId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // 로그인된 사용자 정보 가져오기
        Member member = ((UserDetailsImpl) userDetails).getMember();

        CommentResponseDto responseDto = commentService.createComment(postId, commentRequestDto, member);

        return ResponseEntity
                .ok()
                .body(responseDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Member member = ((UserDetailsImpl) userDetails).getMember();

        commentService.removeComment(commentId, member);

        return ResponseEntity
                .ok()
                .body(Map.entry("msg", "댓글 삭제 완료, commendId: " + commentId));
    }
}
