package com.example.uspot.entity;

import com.example.uspot.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UspotUsers{

    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 8, unique = true)
    private String regisnumber;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 18)
    private String password;

    @Column(nullable = false, length = 11)
    private String phone;

    private Long totalboard;

    private Long totalFollower;

    @Column(nullable = false)
    private LocalDateTime newDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Role role;

    // 보드 양방향 참조
    @OneToMany(mappedBy = "uspotUsers", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    // 팔로잉 양방향 참조
    @OneToMany(mappedBy = "uspotUsers", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Following> followingList = new ArrayList<>();

}
