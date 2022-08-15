package com.mini.coffeenpastebe.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuRequestDto {
    private String menuName;
    private String menuImg;
    private String menuPrice;
    private String menuDesc;
}
