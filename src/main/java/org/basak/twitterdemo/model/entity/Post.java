package org.basak.twitterdemo.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basak.twitterdemo.model.enums.PostState;
import java.time.LocalDateTime;

@Data //@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 300)
    private String content;

    private String media;

    @Column(nullable = false)
    private Long userId;

    private LocalDateTime publishedAt;

    @Builder.Default
    private Integer viewCount = 0;

    @Builder.Default
    private Integer likeCount = 0;

    @Builder.Default
    private Integer commentCount = 0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostState postState;

    @PrePersist
    public void prePersist() {
        publishedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
    }
}
