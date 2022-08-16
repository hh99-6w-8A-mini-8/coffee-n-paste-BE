package com.mini.coffeenpastebe.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    private Long postId;
    private String memberName;
    private String memberNickname;
    private String brandName;
    private String menuName;
    private String postImg;
    private LocalDateTime createAt;
}
