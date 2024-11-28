package com.example.uspot.entity;

import com.example.uspot.entity.date.UspotDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowList extends UspotDate {

    @Id
    @Column(name = "followlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private UspotUsers uspotUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_id")
    private UspotUsers my;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fri_id")
    private UspotUsers fri;

}
