package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.ResponseDto;
import com.mini.coffeenpastebe.domain.menu.dto.MenuRequestDto;
import com.mini.coffeenpastebe.service.MenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu/{brandName}")
    public ResponseDto<?> addMenu(@PathVariable String brandName, @RequestBody MenuRequestDto menuRequestDto) {
        if (menuRequestDto.getMenuName() == null
        || menuRequestDto.getMenuImg() == null
        || menuRequestDto.getMenuPrice() == null
        || menuRequestDto.getMenuDesc() == null) {
            return ResponseDto.fail("정보를 모두 입력해주세요.");
        }
       return menuService.addMenu(brandName, menuRequestDto);
    }

    @PutMapping("/menu/{brandName}/{menuId}")
    public ResponseDto<?> updateMenu(@PathVariable String brandName, @PathVariable Long menuId, @RequestBody MenuRequestDto menuRequestDto) {
        if (menuRequestDto.getMenuName() == null
                || menuRequestDto.getMenuImg() == null
                || menuRequestDto.getMenuPrice() == null
                || menuRequestDto.getMenuDesc() == null) {
            return ResponseDto.fail("정보를 모두 입력해주세요.");
        }
        return menuService.update(brandName, menuId, menuRequestDto);
    }

    @DeleteMapping("/menu/{brandName}/{menuId}")
    public ResponseDto<?> deleteMenu(@PathVariable String brandName, @PathVariable Long menuId) {
        return menuService.delete(brandName, menuId);
    }

    @GetMapping("/menu/{brandName}")
    public ResponseDto<?> findMenuList(@PathVariable String brandName) {
        return menuService.findMenuList(brandName);
    }
}
