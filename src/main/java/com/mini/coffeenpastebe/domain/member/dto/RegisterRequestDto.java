package com.mini.coffeenpastebe.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    private String memberName;
    private String memberNickname;
    private String memberPassword;
    private String memberPasswordConfirm;
}
