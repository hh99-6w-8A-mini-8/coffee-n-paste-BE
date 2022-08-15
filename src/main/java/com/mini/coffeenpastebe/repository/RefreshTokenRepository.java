package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
