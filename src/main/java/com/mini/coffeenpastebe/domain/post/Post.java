package com.mini.coffeenpastebe.domain.post;

import com.mini.coffeenpastebe.domain.Timestamped;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.post.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "MEMBER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "MENU_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String postImg;

    public void update(PostRequestDto postRequestDto) {
        this.content = postRequestDto.getContent();
    }

    public boolean validateMember(Member member) {
        return !this.member.getId().equals(member.getId());
    }

}
