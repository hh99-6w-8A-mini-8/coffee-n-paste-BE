package com.mini.coffeenpastebe.domain.comment;

import com.mini.coffeenpastebe.domain.Timestamped;
import com.mini.coffeenpastebe.domain.comment.dto.CommentRequestDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getCommentContent();
    }

}
