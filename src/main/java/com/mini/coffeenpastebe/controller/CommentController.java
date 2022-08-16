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

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Comment 조회
    @GetMapping("/api/comment")
    public ResponseEntity<?> getComment(@RequestParam("postId") Long postId) {
        List<CommentListResponseDto> commentResponseDto = commentService.getComment(postId);

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
}
