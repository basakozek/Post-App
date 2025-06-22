package org.basak.twitterdemo.service;
import lombok.RequiredArgsConstructor;
import org.basak.twitterdemo.exception.ErrorType;
import org.basak.twitterdemo.exception.TwitterDemoException;
import org.basak.twitterdemo.mapper.PostMapper;
import org.basak.twitterdemo.model.dto.request.PostAtmaRequestDto;
import org.basak.twitterdemo.model.dto.response.ShowAllPostsResponseDto;
import org.basak.twitterdemo.model.entity.Post;
import org.basak.twitterdemo.model.entity.User;
import org.basak.twitterdemo.model.enums.PostState;
import org.basak.twitterdemo.repository.PostRepository;
import org.basak.twitterdemo.util.JwtManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final JwtManager jwtManager;
    private final PostRepository postRepository;
    private final UserService userService;
    private final PostMapper postMapper;

    public void newPost(PostAtmaRequestDto dto) {
        Optional<Long> userId = jwtManager.validateToken(dto.token());
        if (userId.isEmpty()) {
            throw new TwitterDemoException(ErrorType.INVALID_TOKEN);
        }
        Post post = PostMapper.INSTANCE.toEntity(dto);
        post.setUserId(userId.get());
        post.setPostState(PostState.ACTIVE);
        postRepository.save(post);
    }
    public List<ShowAllPostsResponseDto> showAllPosts(String token) {
        Optional<Long> userId = jwtManager.validateToken(token);
        if (userId.isEmpty()) {
            throw new TwitterDemoException(ErrorType.INVALID_TOKEN);
        }
        List<Post> posts = postRepository.findTop100ByPostStateOrderByPublishedAtDesc(PostState.ACTIVE);
        return posts.stream()
                .map(post -> {
                    User user = userService.findById(post.getUserId());
                    return postMapper.toShowAllPostsDto(post, user);
                })
                .toList();
    }
    public List<ShowAllPostsResponseDto> getMyPosts(String token) {
        Optional<Long> userIdOpt = jwtManager.validateToken(token);
        if (userIdOpt.isEmpty()) {
            throw new TwitterDemoException(ErrorType.INVALID_TOKEN);
        }
        Long userId = userIdOpt.get();
        User user = userService.findById(userId);
        List<Post> posts = postRepository.findAllByUserIdAndPostStateOrderByPublishedAtDesc(userId, PostState.ACTIVE);
        return posts.stream()
                .map(post -> postMapper.toShowAllPostsDto(post, user))
                .toList();
    }

}