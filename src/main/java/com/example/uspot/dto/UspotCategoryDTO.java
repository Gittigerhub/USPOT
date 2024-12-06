package com.example.uspot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UspotCategoryDTO {

    private Long num;                                             // 카테고리 ID

    private String cate;                                          // 카테고리명

    private Long boardId;                                         // 게시글 ID

}
