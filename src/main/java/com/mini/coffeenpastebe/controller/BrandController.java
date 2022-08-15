package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import com.mini.coffeenpastebe.service.BrandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/brand")
    public ResponseDto<?> addBrand(@RequestBody BrandRequestDto brandRequestDto) {
        if (brandRequestDto.getBrandName() == null || brandRequestDto.getBrandImg() == null)
            return ResponseDto.fail("정보를 모두 입력해주세요.");
       return brandService.addBrand(brandRequestDto);
    }

    @PutMapping("/brand/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody BrandRequestDto brandRequestDto) {
        if (brandRequestDto.getBrandName() == null || brandRequestDto.getBrandImg() == null)
            return ResponseDto.fail("정보를 모두 입력해주세요.");
        return brandService.update(id, brandRequestDto);
    }

    @DeleteMapping("/brand/{id}")
    public String delete(@PathVariable Long id) {
        return brandService.delete(id);
    }

    @GetMapping("/brand")
    public ResponseDto<?> findAllBrand() {
        return brandService.findBrandList();
    }
}
