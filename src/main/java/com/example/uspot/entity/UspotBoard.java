package com.example.uspot.entity;

import com.example.uspot.entity.date.UspotDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UspotBoard extends UspotDate {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;                           // 게시글ID

    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "장소명은 2~20글자내로 작성해야합니다.")
    private String title;                       // 장소명

    @Column(nullable = false)
    @Size(min = 10, max = 255, message = "내용은 10~255글자내로 작성해야합니다.")
    private String content;                     // 내용

    @Column(nullable = false)
    @Size(min = 2, max = 10, message = "작성자 2~10글자내로 작성해야합니다.")
    private String writer;                      // 작성자

    @Column(nullable = false)
    @Size(min = 6, max = 255, message = "주소는 2~255글자내로 작성해야합니다.")
    private String address;                     // 주소

    @Column(nullable = false)
    private String visitDate;                // 방문일자

    @Column(columnDefinition = "boolean default false")
    private boolean firstRecom;                 // 추천1위글

    @Column(columnDefinition = "boolean default false")
    private boolean stateRecom;                 // 추천 상태

    @Column(columnDefinition = "boolean default false")
    private boolean stateBookMark;              // 북마크 상태

    @Column(columnDefinition = "boolean default false")
    private boolean stateFollow;                // 팔로우 상태

    @Column(columnDefinition = "bigint(20) default 0")
    private Long totalRecom;                    // 총 추천수

    // 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private UspotCategory uspotCategory;        // 카테고리 참조

    // 양방향
    @OneToMany(mappedBy = "uspotBoard", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpotImg> spotImgList;          // 이미지 참조

}
