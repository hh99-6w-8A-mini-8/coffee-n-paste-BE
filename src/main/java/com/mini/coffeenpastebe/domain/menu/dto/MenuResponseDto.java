package com.mini.coffeenpastebe.domain.menu.dto;

import com.mini.coffeenpastebe.domain.menu.Menu;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MenuResponseDto {
    private Long menuId;
    private String menuName;

    public MenuResponseDto(Menu menu) {
        this.menuId = menu.getId();
        this.menuName = menu.getMenuName();
    }
}
