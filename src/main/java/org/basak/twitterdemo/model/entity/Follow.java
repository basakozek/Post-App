package org.basak.twitterdemo.model.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basak.twitterdemo.model.enums.FollowState;

import java.time.LocalDateTime;

@Data //@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_follow", uniqueConstraints = {@UniqueConstraint(columnNames = {"follower_id","followed_id"})})
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="follower_id",nullable = false)
    Long followerId;

    @Column(name="followed_id",nullable = false)
    Long followedId;

    @Column(nullable = false)
    LocalDateTime followedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    FollowState followState;


    @PrePersist
    public void prePersist(){
        followedAt=LocalDateTime.now();
    }

}
