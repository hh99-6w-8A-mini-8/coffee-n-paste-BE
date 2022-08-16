package com.mini.coffeenpastebe.domain.brand.dto;

import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.menu.dto.MenuResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class BrandResponseDto {
    private Long brandId;
    private String brandName;
    private String brandImg;
    private List<MenuResponseDto> menus;
}
