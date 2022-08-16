package com.mini.coffeenpastebe.domain.menu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MenuResponseDto {
    private Long menuId;
    private String menuName;
}
