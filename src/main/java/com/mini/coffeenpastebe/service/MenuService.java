package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.menu.dto.MenuRequestDto;
import com.mini.coffeenpastebe.domain.menu.dto.MenuResponseDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import com.mini.coffeenpastebe.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final BrandRepository brandRepository;

    public MenuService(MenuRepository menuRepository, BrandRepository brandRepository) {
        this.menuRepository = menuRepository;
        this.brandRepository = brandRepository;
    }

    // Todo :: 메뉴 추가
    @Transactional
    public MenuResponseDto create(String brandName, MenuRequestDto menuRequestDto) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            throw new IllegalArgumentException("등록되지 않은 브랜드입니다.");
        }

        Menu menu = Menu.builder()
                .brand(brand)
                .menuName(menuRequestDto.getMenuName())
                .menuImg(menuRequestDto.getMenuImg())
                .menuPrice(menuRequestDto.getMenuPrice())
                .menuDesc(menuRequestDto.getMenuDesc())
                .build();
        menuRepository.save(menu);

        return MenuResponseDto.builder()
                .menuId(menu.getId())
                .menuName(menu.getMenuName())
                .build();
    }

    // Todo :: 메뉴 업데이트
    @Transactional
    public Menu update(String brandName, Long menuId, MenuRequestDto menuRequestDto) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            throw new IllegalArgumentException("등록되지 않은 브랜드입니다.");
        }

        Menu menu = findMenu(menuId, brand);
        if (menu == null) {
            throw new IllegalArgumentException("등록되지 않은 메뉴입니다.");
        }

        menu.update(menuRequestDto);
        return findMenu(menu.getId(), menu.getBrand());
    }

    // Todo :: 메뉴 삭제
    @Transactional
    public String delete(String brandName, Long menuId) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            throw new IllegalArgumentException("등록되지 않은 브랜드입니다.");
        }

        Menu menu = findMenu(menuId, brand);
        if (menu == null) {
            throw new IllegalArgumentException("등록되지 않은 메뉴입니다.");
        }

        menuRepository.delete(menu);

        return "메뉴가 삭제되었습니다.";
    }

    // Todo :: 브랜드 메뉴 조회
    @Transactional(readOnly = true)
    public List<Menu> findAll(String brandName) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            throw new IllegalArgumentException("등록되지 않은 브랜드입니다.");
        }

        return menuRepository.findByBrand(brand);
    }

    @Transactional(readOnly = true)
    public Brand findBrand(String brandName) {
        Optional<Brand> brand = brandRepository.findByBrandName(brandName);
        return brand.orElse(null);
    }

    @Transactional(readOnly = true)
    public Menu findMenu(Long menuId, Brand brand) {
        Optional<Menu> menu = menuRepository.findByIdAndBrand(menuId, brand);
        return menu.orElse(null);
    }
}
