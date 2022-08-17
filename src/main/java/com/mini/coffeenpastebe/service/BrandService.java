package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import com.mini.coffeenpastebe.domain.brand.dto.BrandResponseDto;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.menu.dto.MenuResponseDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    // Todo :: 브랜드 등록
    @Transactional
    public BrandResponseDto create(BrandRequestDto brandRequestDto) {

        Brand brand = Brand.builder()
                .brandName(brandRequestDto.getBrandName())
                .brandImg(brandRequestDto.getBrandImg())
                .build();

        Brand brandSaved = brandRepository.save(brand);

        return new BrandResponseDto(brandSaved);
    }

    // Todo :: 브랜드 이미지 수정
    @Transactional
    public BrandResponseDto update(Long id, BrandRequestDto brandRequestDto){
        Brand brand = isPresentBrand(id);
        brand.update(brandRequestDto);
        Brand updatedBrand = isPresentBrand(id);
        return new BrandResponseDto(updatedBrand);
    }

    // Todo :: 브랜드 삭제
    @Transactional
    public String delete(Long id) {
        brandRepository.deleteById(id);
        return "브랜드 삭제가 완료되었습니다.";
    }

    // Todo :: 브랜드 조회
    @Transactional(readOnly = true)
    public List<BrandResponseDto> findBrandList() {
        List<Brand> brandList = brandRepository.findAll();
        List<BrandResponseDto> brandResponseDtoList = new ArrayList<>();
        for (Brand brand : brandList) {
            List<MenuResponseDto> menuResponseDtoList = new ArrayList<>();
            List<Menu> menuList = brand.getMenuList();
            for (Menu menu : menuList) {
                menuResponseDtoList.add(MenuResponseDto.builder()
                        .menuId(menu.getId())
                        .menuName(menu.getMenuName())
                        .build());
            }
                    brandResponseDtoList.add(
                            BrandResponseDto.builder()
                                    .brandId(brand.getBrandId())
                                    .brandName(brand.getBrandName())
                                    .brandImg(brand.getBrandImg())
                                    .menus(menuResponseDtoList)
                                    .build()
                    );
        }
        return brandResponseDtoList;
    }

    @Transactional(readOnly = true)
    public Brand isPresentBrand(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 브랜드입니다."));
    }
}
