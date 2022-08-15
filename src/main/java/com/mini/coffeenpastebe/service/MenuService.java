package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.menu.dto.MenuRequestDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import com.mini.coffeenpastebe.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResponseDto<?> addMenu(String brandName, MenuRequestDto menuRequestDto) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            return ResponseDto.fail("등록되지 않은 브랜드입니다.");
        }

        Menu menu = Menu.builder()
                .brand(brand)
                .menuName(menuRequestDto.getMenuName())
                .menuImg(menuRequestDto.getMenuImg())
                .menuPrice(menuRequestDto.getMenuPrice())
                .menuDesc(menuRequestDto.getMenuDesc())
                .build();

        menuRepository.save(menu);

        return ResponseDto.success(menu);
    }

    // Todo :: 메뉴 업데이트
    @Transactional
    public ResponseDto<?> update(String brandName, Long menuId, MenuRequestDto menuRequestDto) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            return ResponseDto.fail("등록되지 않은 브랜드입니다.");
        }

        Menu menu = findMenu(menuId, brand);
        if (menu == null) {
            return ResponseDto.fail("등록되지 않은 메뉴입니다.");
        }

        menu.update(menuRequestDto);

        return ResponseDto.success(menu);
    }

    // Todo :: 메뉴 삭제
    @Transactional
    public ResponseDto<?> delete(String brandName, Long menuId) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            return ResponseDto.fail("등록되지 않은 브랜드입니다.");
        }

        Menu menu = findMenu(menuId, brand);
        if (menu == null) {
            return ResponseDto.fail("등록되지 않은 메뉴입니다.");
        }

        menuRepository.delete(menu);

        return ResponseDto.success("메뉴가 삭제되었습니다.");
    }

    // Todo :: 브랜드 메뉴 조회
    @Transactional(readOnly = true)
    public ResponseDto<?> findMenuList(String brandName) {

        Brand brand = findBrand(brandName);
        if (brand == null) {
            return ResponseDto.fail("등록되지 않은 브랜드입니다.");
        }

        List<Menu> menuList = menuRepository.findByBrand(brand);

        return ResponseDto.success(menuList);
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
