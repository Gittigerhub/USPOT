package com.example.uspot.entity;

import com.example.uspot.constant.Role;
import com.example.uspot.entity.date.UspotDate;
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
public class UspotUsers extends UspotDate {

    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 14, unique = true)
    private String regisnumber;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 13)
    private String phone;

    @Column(nullable = false, length = 10)
    private String postcode;

    @Column(nullable = false, length = 50)
    private String address;

    private Long totalboard;

    private Long totalFollower;

    @Enumerated(EnumType.STRING)
    private Role role;




    // 팔로우리스트 참조
    @OneToMany(mappedBy = "uspotUsers", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<FollowList> followLists = new ArrayList<>();

    @OneToMany(mappedBy = "my", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<FollowList> myfollowLists = new ArrayList<>();

    @OneToMany(mappedBy = "fri", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<FollowList> frifollowLists = new ArrayList<>();






}
