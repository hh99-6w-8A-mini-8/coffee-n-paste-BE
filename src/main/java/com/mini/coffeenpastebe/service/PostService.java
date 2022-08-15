package com.mini.coffeenpastebe.service;

import com.mini.coffeenpastebe.domain.brand.Brand;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.menu.Menu;
import com.mini.coffeenpastebe.domain.post.Post;
import com.mini.coffeenpastebe.domain.post.dto.PostRequestDto;
import com.mini.coffeenpastebe.repository.BrandRepository;
import com.mini.coffeenpastebe.repository.MenuRepository;
import com.mini.coffeenpastebe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .postImg(postRequestDto.getPostImg())
                .build();
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Long postId, PostRequestDto postRequestDto, Member member) {

        Post post = isPresentPost(postId);
        if (null == post) {
            throw new IllegalArgumentException("해당 게시물이 존재하지 않습니다.");
        }

        if (post.validateMember(member)) {
            throw new IllegalArgumentException("해당 게시물의 작성자만 수정할 권한이 있습니다.");
        }

        post.update(postRequestDto);
        return post;
    }

    @Transactional(readOnly = true)
    public Post read(Long postId) {
        Post post = isPresentPost(postId);
        if (null == post) {
            throw new IllegalArgumentException("해당 게시물이 존재하지 않습니다");
        }
        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Object findAllMy(Member member) {
        return postRepository.findAllByMember(member);
    }

    @Transactional(readOnly = true)
    public Object findAllByBrand(String brandName) {
        Brand brandSelected = brandRepository.findByBrandName(brandName).orElse(null);
        if (brandSelected == null) {
            throw new IllegalArgumentException("해당 브랜드가 존재하지 않습니다");
        }
        return postRepository.findAllByBrand(brandSelected);
    }



    @Transactional(readOnly = true)
    public Post isPresentPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }



}
