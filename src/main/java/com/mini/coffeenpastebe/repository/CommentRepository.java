package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(Long postId);
}
