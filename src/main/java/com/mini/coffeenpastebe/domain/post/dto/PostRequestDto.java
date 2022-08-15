package com.mini.coffeenpastebe.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private Long brandId;
    private Long menuId;
    private String title;
    private String content;
    private String postImg;
}