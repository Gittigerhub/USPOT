package com.example.uspot.entity;

import com.example.uspot.entity.date.AddDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Following extends AddDate {

    @Id
    @Column(name = "following_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String req_user;

    private String res_user;

}
