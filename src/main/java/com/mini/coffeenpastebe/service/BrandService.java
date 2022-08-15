package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // Todo :: 브랜드 등록
    @Transactional
    public ResponseDto<?> addBrand(BrandRequestDto brandRequestDto) {

        Brand brand = Brand.builder()
                .brandName(brandRequestDto.getBrandName())
                .brandImg(brandRequestDto.getBrandImg())
                .build();

        brandRepository.save(brand);

        return ResponseDto.success(brand);
    }

    // Todo :: 브랜드 이미지 수정
    @Transactional
    public ResponseDto<?> update(Long id, BrandRequestDto brandRequestDto) {
        Brand brand = findBrand(id);
        if (brand == null) {
            return ResponseDto.fail("등록되지 않은 브랜드입니다.");
        }
        brand.update(brandRequestDto);
        return ResponseDto.success(brand);
    }

    // Todo :: 브랜드 삭제
    @Transactional
    public String delete(Long id) {
        brandRepository.deleteById(id);
        return "브랜드 삭제가 완료되었습니다.";
    }

    // Todo :: 브랜드 조회
    @Transactional(readOnly = true)
    public ResponseDto<?> findBrandList() {
        List<Brand> brandList = brandRepository.findAll();
        return ResponseDto.success(brandList);
    }

    @Transactional(readOnly = true)
    public Brand findBrand(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.orElse(null);
    }




}
