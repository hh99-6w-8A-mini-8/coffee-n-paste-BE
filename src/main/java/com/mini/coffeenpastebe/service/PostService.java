package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.post.Post;
import com.mini.coffeenpastebe.domain.post.dto.PostRequestDto;
import com.mini.coffeenpastebe.domain.post.dto.PostResponseDto;
import com.mini.coffeenpastebe.domain.post.dto.Posts;
import com.mini.coffeenpastebe.repository.BrandRepository;
import com.mini.coffeenpastebe.repository.MenuRepository;
import com.mini.coffeenpastebe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MenuRepository menuRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public Post create(PostRequestDto postRequestDto, Member member) {


        Brand brand = brandRepository.findById(postRequestDto.getBrandId()).orElse(null);
        Menu menu = menuRepository.findByIdAndBrand(postRequestDto.getMenuId(), brand).orElse(null);

        Post post = Post.builder()
                .member(member)
                .menu(menu)
                .content(postRequestDto.getContent())
                .postImg(postRequestDto.getPostImg())
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public PostResponseDto update(Long postId, PostRequestDto postRequestDto, Member member) {

        Post post = isPresentPost(postId);
        if (null == post) {
            throw new IllegalArgumentException("해당 게시물이 존재하지 않습니다.");
        }

        if (post.validateMember(member)) {
            throw new IllegalArgumentException("해당 게시물의 작성자만 수정할 권한이 있습니다.");
        }

        post.update(postRequestDto);

        return PostResponseDto.builder()
                .postId(post.getId())
                .memberNickName(post.getMember().getMemberNickname())
                .brandName(post.getMenu().getBrand().getBrandName())
                .menuName(post.getMenu().getMenuName())
                .postContent(post.getContent())
                .postImg(post.getPostImg())
                .createAt(post.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public PostResponseDto read(Long postId) {

        Post post = isPresentPost(postId);
        if (post == null) {
            throw new IllegalArgumentException("해당 게시물이 존재하지 않습니다.");
        }

        return PostResponseDto.builder()
                .postId(post.getId())
                .memberName(post.getMember().getMemberName())
                .memberNickName(post.getMember().getMemberNickname())
                .brandName(post.getMenu().getBrand().getBrandName())
                .menuName(post.getMenu().getMenuName())
                .postContent(post.getContent())
                .postImg(post.getPostImg())
                .createAt(post.getCreatedAt())
                .build();
        // comment 완료시 추가.

    }

    @Transactional(readOnly = true)
    public List<Posts> findAll() {

        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();

        List<Posts> posts = new ArrayList<>();

        for (Post post : postList) {

            posts.add(
                    Posts.builder()
                            .postId(post.getId())
                            .memberName(post.getMember().getMemberName())
                            .memberNickname(post.getMember().getMemberNickname())
                            .brandName(post.getMenu().getBrand().getBrandName())
                            .menuName(post.getMenu().getMenuName())
                            .postImg(post.getPostImg())
                            .createAt(post.getCreatedAt())
                            .build()
            );
        }

        return posts;
    }

    @Transactional(readOnly = true)
    public List<Posts> findAllMy(Member member) {

        List<Post> postList = postRepository.findAllByMember(member);

        List<Posts> posts = new ArrayList<>();

        for (Post post : postList) {
            posts.add(
                    Posts.builder()
                            .postId(post.getId())
                            .memberName(post.getMember().getMemberName())
                            .memberNickname(post.getMember().getMemberNickname())
                            .brandName(post.getMenu().getBrand().getBrandName())
                            .menuName(post.getMenu().getMenuName())
                            .postImg(post.getPostImg())
                            .createAt(post.getCreatedAt())
                            .build()
            );
        }

        return posts;
    }

    @Transactional(readOnly = true)
    public List<Posts> findAllByBrand(String brandName) {
        Brand brandSelected = brandRepository.findByBrandName(brandName).orElse(null);
        if (brandSelected == null) {
            throw new IllegalArgumentException("해당 브랜드가 존재하지 않습니다");
        }
        List<Post> postList =  postRepository.findAllByMenu_Brand(brandSelected);
        List<Posts> posts = new ArrayList<>();

        for (Post post : postList) {
            posts.add(
                    Posts.builder()
                            .postId(post.getId())
                            .memberName(post.getMember().getMemberName())
                            .memberNickname(post.getMember().getMemberNickname())
                            .brandName(post.getMenu().getBrand().getBrandName())
                            .menuName(post.getMenu().getMenuName())
                            .postImg(post.getPostImg())
                            .createAt(post.getCreatedAt())
                            .build()
            );
        }

        return posts;
    }



    @Transactional(readOnly = true)
    public Post isPresentPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }



}
