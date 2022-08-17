package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.menu.dto.MenuRequestDto;
import com.mini.coffeenpastebe.domain.menu.dto.MenuResponseDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import com.mini.coffeenpastebe.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    void createTest() {
        //given
        String brandName = "서타벅서";

        String menuName = "아메리카노";
        String menuImg = "이미지";
        Long menuPrice = 5000L;
        String menuDesc = "서타벅서 아메리카노";

        MenuRequestDto requestDto = new MenuRequestDto(menuName, menuImg, menuPrice, menuDesc);

        when(brandRepository.findByBrandName(brandName)).thenReturn(java.util.Optional.of(new Brand()));

        //when
        MenuResponseDto menuResponseDto = menuService.create(brandName, requestDto);

        //then
        assertThat(menuResponseDto.getMenuName()).isEqualTo(menuName);
    }
}