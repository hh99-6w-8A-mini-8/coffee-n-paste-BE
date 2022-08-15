package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.menu.dto.MenuRequestDto;
import com.mini.coffeenpastebe.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/api/menu/{brandName}")
    public ResponseEntity<?> create(@PathVariable String brandName, @RequestBody MenuRequestDto menuRequestDto) {
        if (menuRequestDto.getMenuName() == null
        || menuRequestDto.getMenuImg() == null
        || menuRequestDto.getMenuPrice() == null
        || menuRequestDto.getMenuDesc() == null) {
            return new ResponseEntity<>("정보를 모두 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(menuService.create(brandName, menuRequestDto), HttpStatus.OK);
    }

    @PutMapping("/api/menu/{brandName}/{menuId}")
    public ResponseEntity<?> update(@PathVariable String brandName, @PathVariable Long menuId, @RequestBody MenuRequestDto menuRequestDto) {
        if (menuRequestDto.getMenuName() == null
                || menuRequestDto.getMenuImg() == null
                || menuRequestDto.getMenuPrice() == null
                || menuRequestDto.getMenuDesc() == null) {
            return new ResponseEntity<>("정보를 모두 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(menuService.update(brandName, menuId, menuRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/api/menu/{brandName}/{menuId}")
    public ResponseEntity<?> delete(@PathVariable String brandName, @PathVariable Long menuId) {
        return new ResponseEntity<>(menuService.delete(brandName, menuId), HttpStatus.OK) ;
    }

    @GetMapping("/api/menu/{brandName}")
    public ResponseEntity<?> findAll(@PathVariable String brandName) {
        return new ResponseEntity<>(menuService.findAll(brandName), HttpStatus.OK) ;
    }
}
