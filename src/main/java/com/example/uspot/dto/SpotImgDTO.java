package com.example.uspot.dto;

import com.example.uspot.entity.UspotBoard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SpotImgDTO {

    private Long num;                       // 이미지ID

    private String imgName;                 // 파일명

    private String originalName;            // 원본파일명

    private String imgPath;                 // 이미지 조회 경로

    private String repimgYn;                // 대표이미지 여부

    private UspotBoardDTO uspotBoardDTO;    // uspotBoardDTO

}
