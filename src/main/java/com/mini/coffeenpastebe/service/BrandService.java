package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.jwt.TokenProvider;
import com.mini.coffeenpastebe.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;


    // Todo :: 브랜드 등록
    @Transactional
    public Brand create(BrandRequestDto brandRequestDto) {

        Brand brand = Brand.builder()
                .brandName(brandRequestDto.getBrandName())
                .brandImg(brandRequestDto.getBrandImg())
                .build();

        return brandRepository.save(brand);
    }

    // Todo :: 브랜드 이미지 수정
    @Transactional
    public Brand update(Long id, BrandRequestDto brandRequestDto){
        Brand brand = findBrand(id);
        if (brand == null) {
            throw new IllegalArgumentException("등록되지 않은 브랜드입니다.");
        }
        brand.update(brandRequestDto);
        Brand updatedBrand = findBrand(id);
        return updatedBrand;
    }

    // Todo :: 브랜드 삭제
    @Transactional
    public String delete(Long id) {
        brandRepository.deleteById(id);
        return "브랜드 삭제가 완료되었습니다.";
    }

    // Todo :: 브랜드 조회
    @Transactional(readOnly = true)
    public List<Brand> findBrandList() {
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Brand findBrand(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.orElse(null);
    }
}
