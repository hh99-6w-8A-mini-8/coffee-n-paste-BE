package com.mini.coffeenpastebe.controller;

import com.mini.coffeenpastebe.domain.UserDetailsImpl;
import com.mini.coffeenpastebe.domain.member.Member;
import com.mini.coffeenpastebe.domain.post.dto.PostRequestDto;
import com.mini.coffeenpastebe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Todo :: 게시글 등록
    @RequestMapping(value = "/api/post", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        Member member =  ((UserDetailsImpl) userDetails).getMember();

        postService.create(postRequestDto, member);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Todo :: 게시글 단건 수정 --> 추후 commentList 추가 (PostAndCommentDto)
    @RequestMapping(value = "/api/post/{postId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        Member member =  ((UserDetailsImpl) userDetails).getMember();
        return new ResponseEntity<>(postService.update(postId, postRequestDto, member), HttpStatus.OK);
    }

    // Todo :: 게시글 단건 조회 --> 추후 commentList 추가 (PostAndCommentDto)
    @RequestMapping(value = "/api/post/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> read(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.read(postId), HttpStatus.OK);
    }

    // Todo :: 메인페이지 전체리뷰 리스트
    @RequestMapping(value = "/api/posts", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    // Todo :: 나의 게시글 불러오기
    @RequestMapping(value = "/api/my-post", method = RequestMethod.GET)
    public ResponseEntity<?> findAllMy(@AuthenticationPrincipal UserDetails userDetails) {
        Member member = ((UserDetailsImpl) userDetails).getMember();
        return new ResponseEntity<>(postService.findAllMy(member), HttpStatus.OK);
    }

    // Todo :: 브랜드의 모든 리뷰 불러오기
    @RequestMapping(value = "/api/post", method = RequestMethod.GET)
    public ResponseEntity<?> findAllByBrand(@RequestParam("brand") String brand) {
        return new ResponseEntity<>(postService.findAllByBrand(brand), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/post/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails) {
        Member member = ((UserDetailsImpl) userDetails).getMember();
        return new ResponseEntity<>(postService.delete(postId, member), HttpStatus.OK);
    }

}
