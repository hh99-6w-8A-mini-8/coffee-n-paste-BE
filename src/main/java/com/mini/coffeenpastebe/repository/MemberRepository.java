package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberNameAndPassword(String memberName, String password);

    boolean existsByMemberName(String memberName);

    Optional<Member> findByMemberName(String memberName);
}
