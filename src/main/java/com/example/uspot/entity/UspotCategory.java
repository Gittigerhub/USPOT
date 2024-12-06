package com.example.uspot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UspotCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;                                             // 카테고리 ID

    private String cate;                                          // 카테고리명

    // 양방향
    @OneToMany(mappedBy = "uspotCategory", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<UspotBoard> uspotBoardList = new ArrayList<>();  // 게시글 참조


}
