package com.mini.coffeenpastebe.domain.member;

import com.mini.coffeenpastebe.domain.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberNickname;

    @Column(nullable = false)
    private String password;
}
