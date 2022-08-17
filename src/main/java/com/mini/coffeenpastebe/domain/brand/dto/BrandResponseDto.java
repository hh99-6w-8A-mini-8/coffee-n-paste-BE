package com.mini.coffeenpastebe.domain.brand.dto;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.menu.dto.MenuResponseDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BrandResponseDto {
    private Long brandId;
    private String brandName;
    private String brandImg;
    private List<MenuResponseDto> menus;

    public BrandResponseDto(Brand brand) {
        this.brandId = brand.getBrandId();
        this.brandName = brand.getBrandName();
        this.brandImg = brand.getBrandImg();
        this.menus = toMenuResponseDto(brand.getMenuList());
    }

    public List<MenuResponseDto> toMenuResponseDto(List<Menu> menuList) {
        return menuList.stream().map(menu -> MenuResponseDto.builder()
                .menuId(menu.getId())
                .menuName(menu.getMenuName())
                .build()).collect(Collectors.toList());
    }

}
