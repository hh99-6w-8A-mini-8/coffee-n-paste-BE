package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.RefreshToken;
import com.mini.coffeenpastebe.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByMember(Member member);
}
