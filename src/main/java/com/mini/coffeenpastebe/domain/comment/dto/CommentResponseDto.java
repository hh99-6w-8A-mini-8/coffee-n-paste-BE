package com.mini.coffeenpastebe.domain.comment.dto;

import com.mini.coffeenpastebe.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long commentId;
    private String memberName;
    private String memberNickname;
    private String commentContent;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.memberName = comment.getMember().getMemberName();
        this.memberNickname = comment.getMember().getMemberNickname();
        this.commentContent = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
