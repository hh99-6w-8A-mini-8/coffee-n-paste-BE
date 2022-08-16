package com.mini.coffeenpastebe.repository;

import com.mini.coffeenpastebe.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
