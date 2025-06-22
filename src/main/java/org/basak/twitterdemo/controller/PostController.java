package org.basak.twitterdemo.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.basak.twitterdemo.constant.EndPoints;
import org.basak.twitterdemo.model.dto.request.PostAtmaRequestDto;
import org.basak.twitterdemo.model.dto.response.BaseResponse;
import org.basak.twitterdemo.model.dto.response.ShowAllPostsResponseDto;
import org.basak.twitterdemo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(EndPoints.POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/new-post")
    public ResponseEntity<BaseResponse<Boolean>> newPost(@RequestBody @Valid PostAtmaRequestDto dto) {
        postService.newPost(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .success(true)
                .message("Post atma başarılı")
                .build());
    }
    @GetMapping("/show-all-posts")
    public ResponseEntity<BaseResponse<List<ShowAllPostsResponseDto>>> showAllPosts(@RequestParam String token) {
        List<ShowAllPostsResponseDto> posts = postService.showAllPosts(token);
        return ResponseEntity.ok(BaseResponse.<List<ShowAllPostsResponseDto>>builder()
                .code(200)
                .data(posts)
                .success(true)
                .message("Postlar başarıyla getirildi")
                .build());
    }
    @GetMapping("/show-my-posts")
    public ResponseEntity<BaseResponse<List<ShowAllPostsResponseDto>>> getMyPosts(@RequestParam String token) {
        List<ShowAllPostsResponseDto> posts = postService.getMyPosts(token);
        return ResponseEntity.ok(BaseResponse.<List<ShowAllPostsResponseDto>>builder()
                .code(200)
                .data(posts)
                .success(true)
                .message("Kullanıcının postları başarıyla getirildi")
                .build());
    }
}
