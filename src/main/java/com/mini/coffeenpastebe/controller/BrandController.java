package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import com.mini.coffeenpastebe.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/api/brand")
    public ResponseEntity<?> create(@RequestBody BrandRequestDto brandRequestDto) {
        if (brandRequestDto.getBrandName() == null || brandRequestDto.getBrandImg() == null)
            return new ResponseEntity<>("정보를 모두 입력해주세요.", HttpStatus.BAD_REQUEST);
       return new ResponseEntity<>(brandService.create(brandRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/api/brand/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BrandRequestDto brandRequestDto) {
        if (brandRequestDto.getBrandName() == null || brandRequestDto.getBrandImg() == null)
            return new ResponseEntity<>("정보를 모두 입력해주세요.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(brandService.update(id, brandRequestDto), HttpStatus.OK) ;
    }

    @DeleteMapping("/api/brand/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(brandService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/api/brand")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(brandService.findBrandList(), HttpStatus.OK);
    }
}
