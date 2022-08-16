package com.mini.coffeenpastebe.domain.post.dto;

import com.mini.coffeenpastebe.domain.comment.dto.CommentListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailsResponseDto {
    private Long postId;
    private String memberName;
    private String memberNickName;
    private String brandName;
    private String menuName;
    private String postContent;
    private List<CommentListResponseDto> commentListResponseDtoList;
    private String postImg;
    private LocalDateTime createAt;
}
