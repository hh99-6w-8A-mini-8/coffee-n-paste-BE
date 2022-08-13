package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
