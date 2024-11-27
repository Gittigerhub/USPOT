package com.example.uspot.entity;

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
public class UspotCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String restaurant;

    private String Pension;

    private String nature;

    private String themepark;

    private String jjimjilbang;

    private String cafe;

    private String drive;

    @OneToMany(mappedBy = "uspotCategory", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

}
