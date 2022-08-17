package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.comment.dto.CommentResponseDto;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.post.Post;
import com.mini.coffeenpastebe.domain.post.dto.PostBasicResponseDto;
import com.mini.coffeenpastebe.domain.post.dto.PostDetailsResponseDto;
import com.mini.coffeenpastebe.domain.post.dto.PostRequestDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import com.mini.coffeenpastebe.repository.CommentRepository;
import com.mini.coffeenpastebe.repository.MenuRepository;
import com.mini.coffeenpastebe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MenuRepository menuRepository;
    private final BrandRepository brandRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long create(PostRequestDto postRequestDto, Member member) {

        Brand brand = brandRepository.findById(postRequestDto.getBrandId()).orElseThrow(
                ()->new IllegalArgumentException("등록되지 않은 브랜드입니다."));
        Menu menu = isPresentMenu(postRequestDto.getMenuId(), brand);

        Post post = Post.builder()
                .member(member)
                .menu(menu)
                .content(postRequestDto.getContent())
                .postImg(postRequestDto.getPostImg())
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional
    public PostDetailsResponseDto update(Long postId, PostRequestDto postRequestDto, Member member) {

        Post post = isPresentPost(postId);

        if (post.validateMember(member)) {
            throw new IllegalArgumentException("해당 게시물의 작성자만 수정할 권한이 있습니다.");
        }

        post.update(postRequestDto);

        return PostDetailsResponseDto.builder()
                .postId(post.getId())
                .memberName(post.getMember().getMemberName())
                .memberNickName(post.getMember().getMemberNickname())
                .brandName(post.getMenu().getBrand().getBrandName())
                .menuName(post.getMenu().getMenuName())
                .postContent(post.getContent())
                .postImg(post.getPostImg())
                .createAt(post.getCreatedAt())
                .comments(commentRepository.findByPost_Id(post.getId()).stream().map(CommentResponseDto::new).collect(Collectors.toList()))
                .build();
    }

    @Transactional(readOnly = true)
    public PostDetailsResponseDto read(Long postId) {

        Post post = isPresentPost(postId);

        return PostDetailsResponseDto.builder()
                .postId(post.getId())
                .memberName(post.getMember().getMemberName())
                .memberNickName(post.getMember().getMemberNickname())
                .brandName(post.getMenu().getBrand().getBrandName())
                .menuName(post.getMenu().getMenuName())
                .postContent(post.getContent())
                .postImg(post.getPostImg())
                .createAt(post.getCreatedAt())
                .comments(commentRepository.findByPost_Id(post.getId()).stream().map(CommentResponseDto::new).collect(Collectors.toList()))
                .build();
        // comment 완료시 추가.
    }

    @Transactional(readOnly = true)
    public Page<PostBasicResponseDto> findAll(Pageable pageable) {

        Page<Post> postList = postRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<PostBasicResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {
            posts.add(
                    PostBasicResponseDto.builder()
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

        return new PageImpl(posts, postList.getPageable(), postList.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<PostBasicResponseDto> findAllMy(Member member, Pageable pageable) {

        Page<Post> postList = postRepository.findAllByMember(member, pageable);

        List<PostBasicResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {
            posts.add(
                    PostBasicResponseDto.builder()
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

        return new PageImpl(posts, postList.getPageable(), postList.getTotalElements());
    }

    @Transactional(readOnly = true)
    public List<PostBasicResponseDto> findAllByBrand(String brandName) {
        Brand brandSelected = isPresentBrand(brandName);
        if (brandSelected == null) {
            throw new IllegalArgumentException("해당 브랜드가 존재하지 않습니다");
        }
        List<Post> postList =  postRepository.findAllByMenu_Brand(brandSelected);
        List<PostBasicResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {
            posts.add(
                    PostBasicResponseDto.builder()
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

    @Transactional
    public String delete(Long postId, Member member) {
        Post post = isPresentPost(postId);

        if(post.validateMember(member)) {
            throw new IllegalArgumentException("해당 게시물의 작성자만 삭제할 권한이 있습니다");
        }
        postRepository.delete(post);
        return "게시물이 정상적으로 삭제되었습니다.";
    }

    @Transactional(readOnly = true)
    public Brand isPresentBrand(String brandName) {
        Optional<Brand> brand = brandRepository.findByBrandName(brandName);
        return brand.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 브랜드입니다."));
    }

    @Transactional(readOnly = true)
    public Menu isPresentMenu(Long menuId, Brand brand) {
        Optional<Menu> menu = menuRepository.findByIdAndBrand(menuId, brand);
        return menu.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 메뉴입니다."));
    }

    @Transactional(readOnly = true)
    public Post isPresentPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(()-> new IllegalArgumentException("등록되지 않은 게시물입니다."));
    }

}
