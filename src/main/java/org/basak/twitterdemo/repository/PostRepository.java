package org.basak.twitterdemo.repository;
import org.basak.twitterdemo.model.entity.Post;
import org.basak.twitterdemo.model.enums.PostState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop100ByPostStateOrderByPublishedAtDesc(PostState postState);

    List<Post> findAllByUserIdAndPostStateOrderByPublishedAtDesc(Long userId, PostState postState);
}
