package com.mini.coffeenpastebe.domain.brand;

import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import com.mini.coffeenpastebe.domain.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Brand {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name = "BRAND_NAME", nullable = false)
    private String brandName;

    @Column(name = "BRAND_IMG", nullable = false)
    private String brandImg;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Menu> menuList = new ArrayList<>();

    public void update (BrandRequestDto brandRequestDto) {
        this.brandName = brandRequestDto.getBrandName();
        this.brandImg = brandRequestDto.getBrandImg();
    }
}
