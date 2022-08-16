package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.TokenDto;
import com.mini.coffeenpastebe.domain.UserDetailsImpl;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.member.dto.CheckMemberResponseDto;
import com.mini.coffeenpastebe.domain.member.dto.LoginRequestDto;
import com.mini.coffeenpastebe.domain.member.dto.MemberResponseDto;
import com.mini.coffeenpastebe.domain.member.dto.RegisterRequestDto;
import com.mini.coffeenpastebe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        TokenDto token = memberService.login(loginRequestDto);

        // response header에 담기
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAuthorization());
        headers.add("Refresh-Token", token.getRefreshToken());
        // 200 코드로 리턴
        return ResponseEntity.ok()
                .headers(headers)
                .body(Map.entry("msg", "로그인 완료"));
    }

    // 회원가입
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        MemberResponseDto memberResponseDto = memberService.register(registerRequestDto);

        return ResponseEntity.ok()
                .body(memberResponseDto);
    }

    // 중복된 아이디 검사
    // 이미 존재하는 아이디라면 true
    @GetMapping("/api/register")
    public ResponseEntity<?> checkMemberName(@RequestParam("memberName") String memberName) {
        CheckMemberResponseDto responseDto = memberService.checkMemberName(memberName);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @DeleteMapping("/api/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDetails userDetails) {
        Member member = ((UserDetailsImpl) userDetails).getMember();

        memberService.logout(member);
        return ResponseEntity.ok()
                .body(Map.entry("msg", "로그아웃 완료"));
    }
}
