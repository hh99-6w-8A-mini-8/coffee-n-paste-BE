package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.TokenDto;
import com.mini.coffeenpastebe.domain.member.dto.LoginRequestDto;
import com.mini.coffeenpastebe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인
    @GetMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        TokenDto token = memberService.login(loginRequestDto);

        // response header에 담기
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAuthorization());
        headers.add("refresh-token", token.getRefreshToken());
        // 200 코드로 리턴
        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }
}
