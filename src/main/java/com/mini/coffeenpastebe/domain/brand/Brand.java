package com.mini.coffeenpastebe.domain.brand;

import com.mini.coffeenpastebe.domain.brand.dto.BrandRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    // starbucks main banner exam
    // www.aseanexpress.co.kr/data/photos/20211043/art_16350988026128_3c8e09.png
    // https://image.istarbucks.co.kr/cardImg/20220711/009207_WEB.png

    public void update (BrandRequestDto brandRequestDto) {
        this.brandName = brandRequestDto.getBrandName();
        this.brandImg = brandRequestDto.getBrandImg();
    }
}
