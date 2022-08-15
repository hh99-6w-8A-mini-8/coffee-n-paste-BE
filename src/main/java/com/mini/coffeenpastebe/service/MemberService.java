package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.TokenDto;
import com.mini.coffeenpastebe.domain.UserDetailsImpl;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.member.dto.CheckMemberResponseDto;
import com.mini.coffeenpastebe.domain.member.dto.LoginRequestDto;
import com.mini.coffeenpastebe.domain.member.dto.RegisterRequestDto;
import com.mini.coffeenpastebe.domain.member.dto.MemberResponseDto;
import com.mini.coffeenpastebe.jwt.TokenProvider;
import com.mini.coffeenpastebe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    // 로그인 로직
    public TokenDto login(LoginRequestDto loginRequestDto) {
        String memberName = loginRequestDto.getMemberName();

        // 데이터베이스에 저장된 사용자 찾아오기
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
                });

        if (!passwordEncoder.matches(loginRequestDto.getMemberPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        // member 객체를 이용하여 Authenticaion 객체 만들기
        UserDetailsImpl userDetails = new UserDetailsImpl(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 만든 토큰 리턴하기
        return tokenDto;
    }

    // 회원 가입
    public MemberResponseDto register(RegisterRequestDto registerRequestDto) {
        // 비밀번호 암호화
        String password = passwordEncoder.encode(registerRequestDto.getMemberPassword());

        // 맴버 객체 생성
        Member member = Member.builder()
                .memberName(registerRequestDto.getMemberName())
                .memberNickname(registerRequestDto.getMemberNickname())
                .password(password)
                .build();

        // 저장 후 리턴
        memberRepository.save(member);

        // Response 리턴 값
        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .build();
        return memberResponseDto;
    }

    // 사용자 아이디 중복 검사
    public CheckMemberResponseDto checkMemberName(String memberName) {

        boolean isMember = memberRepository.existsByMemberName(memberName);
        CheckMemberResponseDto checkMemberResponseDto = CheckMemberResponseDto.builder()
                .memberName(memberName)
                .checkMember(isMember)
                .build();

        return checkMemberResponseDto;
    }
}
