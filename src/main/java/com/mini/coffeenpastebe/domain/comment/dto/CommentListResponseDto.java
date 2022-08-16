package com.mini.coffeenpastebe.domain.comment.dto;

import com.mini.coffeenpastebe.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListResponseDto {
    private Long postId;
    private List<CommentResponseDto> comments;
}
