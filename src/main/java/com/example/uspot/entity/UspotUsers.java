package com.example.uspot.entity;

import com.example.uspot.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UspotUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;
    private String regisnumber;
    private String email;
    private String password;
    private String phone;
    private Long totalboard;
    private Long totalFollower;

    @Enumerated
    private Role role;

    @
    private Long following_id;
    private Long board_id;

}
