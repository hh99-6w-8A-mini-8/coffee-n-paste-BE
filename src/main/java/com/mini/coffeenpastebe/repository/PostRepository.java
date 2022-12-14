package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Post> findAllByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);

    Page<Post> findAllByMenu_BrandAndMenu_MenuNameOrderByCreatedAtDesc(Brand brand, String menuName, Pageable pageable);

    Page<Post> findAllByMenu_BrandOrderByCreatedAtDesc(Brand brandSelected, Pageable pageable);
}
