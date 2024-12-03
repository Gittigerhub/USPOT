package com.example.uspot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SpotImg {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;                   // 이미지ID

    private String imgName;             // 파일명

    private String originalName;        // 원본파일명

    private String imgPath;             // 이미지 조회 경로

    private String repimgYn;            // 대표이미지 여부


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private UspotBoard uspotBoard;

}
