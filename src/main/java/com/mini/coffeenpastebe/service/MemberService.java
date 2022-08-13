package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.TokenDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.member.dto.LoginRequestDto;
import com.mini.coffeenpastebe.domain.member.dto.RegisterRequestDto;
import com.mini.coffeenpastebe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 로그인 로직
    public TokenDto login(LoginRequestDto loginRequestDto) {
        String memberName = loginRequestDto.getMemberName();
        String password = loginRequestDto.getMemberPassword();

        // 데이터베이스에 저장된 사용자 찾아오기
        Member member = memberRepository.findByMemberNameAndPassword(memberName, password)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
                });

        // 사용자 정보로 토큰 만들기

        // 만든 토큰 리턴하기
        return new TokenDto("TestAccessToken+" + member.getMemberNickname(), "TestRefreshToken+" + member.getMemberNickname());
    }

    // 회원 가입
    public Member register(RegisterRequestDto registerRequestDto) {
        Member member = Member.builder()
                .memberName(registerRequestDto.getMemberName())
                .memberNickname(registerRequestDto.getMemberNickname())
                .password(registerRequestDto.getMemberPassword())
                .build();
        memberRepository.save(member);
        return member;
    }
}
