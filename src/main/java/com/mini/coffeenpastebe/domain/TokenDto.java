package com.mini.coffeenpastebe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String authorization;
    private String refreshToken;
}