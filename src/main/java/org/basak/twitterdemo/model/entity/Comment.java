package org.basak.twitterdemo.model.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basak.twitterdemo.model.enums.CommentState;

import java.time.LocalDateTime;

@Data //@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long postId;
    @Column(nullable = false)
    Long userId;
    @Column(nullable = false, length = 500)
    String comment;
    LocalDateTime publishedAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    CommentState commentState;


    @PrePersist
    public void prePersist() {
        publishedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
    }

}
