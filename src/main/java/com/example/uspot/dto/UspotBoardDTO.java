package com.example.uspot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UspotBoardDTO {


    private Long num;                           // 게시글ID

    @NotNull
    @Size(min = 2, max = 20, message = "장소명은 2~20자로 작성해야합니다.")
    private String title;                       // 장소명

    @NotNull
    @Size(min = 10, max = 255, message = "내용은 10~255자로 작성해야합니다.")
    private String content;                     // 내용

    @NotNull
    @Size(min = 2, max = 10, message = "작성자명은 2~10자로 작성해야합니다.")
    private String writer;                      // 작성자

    @NotNull
    @Size(min = 6, max = 255, message = "주소는 6~255자로 작성해야합니다.")
    private String address;                     // 주소

    @NotNull(message = "방문일자는 반드시 작성해야합니다.")
    private LocalDate visitDate;                // 방문일자

    private boolean firstRecom;                 // 추천1위글

    private boolean stateRecom;                 // 추천 상태

    private boolean stateBookMark;              // 북마크 상태

    private boolean stateFollow;                // 팔로우 상태

    private Long totalRecom;                    // 총 추천수

    private LocalDateTime regisDate;            // 생성날짜

    private LocalDateTime updateDate;           // 수정날짜

    private UspotCategoryDTO uspotCategoryDTO;  // 카테고리 DTO

}
