package com.example.uspot.entity;

import com.example.uspot.entity.date.UspotDate;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends UspotDate {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false, length = 10)
    private String writer;

    @Column(nullable = false, length = 255)
    private String address;

    private String firstRecom;

    private String totalRecom;

    private String stateRecom;

    private String stateBookMark;

    private String stateFollowing;

    // 북마크 기능 구현
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<BookMark> bookMarkList = new ArrayList<>();

    // 유저 양방향 조회
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private UspotUsers uspotUsers;

    // 팔로잉 조회
    @OneToOne
    @JoinColumn(name = "following_id")
    private Following following;

    // 카테고리 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private UspotCategory uspotCategory;

    // 좋아요 기능 구현
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Recommend> recommendList = new ArrayList<>();


}
