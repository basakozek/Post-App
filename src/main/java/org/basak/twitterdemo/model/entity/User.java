package org.basak.twitterdemo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basak.twitterdemo.model.enums.AccountState;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;

    @Column( length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    private String avatar;

    @Column(length = 15, unique = true)
    private String phone;

    @Column(length = 500)
    private String biography;


    @Enumerated(EnumType.STRING)
    private AccountState accountState;

    private LocalDate bday;

    private int followerCount = 0;

    private int followedCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private Boolean isVisible;

    @Column(nullable = false)
    private Boolean isVerified;

    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
        isVisible = true;
        isVerified = false;
    }

    @PreUpdate
    public void preUpdate() {
        updateAt = LocalDateTime.now();
    }
    @Column(length = 6)
    private String activationCode;

    @Column
    private LocalDateTime activationCodeExpireDate;
}
