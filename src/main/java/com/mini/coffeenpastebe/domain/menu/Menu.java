package com.mini.coffeenpastebe.domain.menu;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.menu.dto.MenuRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @JoinColumn(name = "BRAND_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @Column(name = "MENU_NAME", nullable = false)
    private String menuName;

    @Column(name = "MENU_IMG", nullable = false)
    private String menuImg;

    @Column(name = "MENU_PRICE", nullable = false)
    private Long menuPrice;

    @Column(name = "MENU_DESC", nullable = false)
    private String menuDesc;

    public void update (MenuRequestDto menuRequestDto) {
        this.menuName = menuRequestDto.getMenuName();
        this.menuImg = menuRequestDto.getMenuImg();
        this.menuPrice = menuRequestDto.getMenuPrice();
        this.menuDesc = menuRequestDto.getMenuDesc();
    }
}
