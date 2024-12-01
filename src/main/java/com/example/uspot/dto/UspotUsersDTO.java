package com.example.uspot.dto;

import com.example.uspot.constant.Role;
import com.example.uspot.entity.UspotUsers;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UspotUsersDTO {

    private Long num;                                       // 유저 ID

    @NotNull
    @Size(min = 2, max = 10, message = "이름은 2~10자로 작성해야합니다.")
    private String name;                                    // 유저 이름

    @NotNull
    @Size(min = 14, max = 14, message = "'-'를 포함한 14자리의 정확한 주민등록번호를 작성해야합니다.")
    private String regisnumber;                             // 주민등록번호

    @NotNull
    @Size(min = 4, max = 50, message = "이메일은 3~50자로 작성해야합니다.")
    private String email;                                   // 이메일

    @NotNull
    @Size(min = 8, max = 18, message = "비밀번호는 8~18자로 작성해야합니다.")
    private String password;                                // 패스워드

    @NotNull
    @Size(min = 13, max = 13, message = "'-'를 포함한 13자리의 정확한 전화번호를 작성해야합니다.")
    private String phone;                                   // 전화번호

    @NotNull
    @Size(min = 2, max = 10, message = "우편번호를 입력해주십시요")
    private String postcode;                                // 우편번호

    @NotNull
    @Size(min = 10, max = 50, message = "10~50자로 정확한 주소를 작성해야합니다.")
    private String address;                                 // 주소

    private Long totalboard;                                // 총게시물 수

    private Long totalFollower;                             // 총팔로우 수

    private Role role;                                      // 권환

    private LocalDateTime regisDate;

    private LocalDateTime updateDate;



    public UspotUsers dtoToEntity (UspotUsersDTO uspotUsersDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UspotUsers uspotUsers = new UspotUsers();
        uspotUsers.setName(uspotUsersDTO.getName());
        uspotUsers.setEmail(uspotUsersDTO.getEmail());
        uspotUsers.setRegisnumber(uspotUsersDTO.getRegisnumber());
        uspotUsers.setPhone(uspotUsersDTO.getPhone());
        uspotUsers.setRegisDate(uspotUsersDTO.getRegisDate());
        uspotUsers.setUpdateDate(uspotUsersDTO.getUpdateDate());
        uspotUsers.setPostcode(uspotUsersDTO.getPostcode());
        uspotUsers.setAddress(uspotUsersDTO.getAddress());

        uspotUsers.setPassword(passwordEncoder.encode(uspotUsersDTO.getPassword()));
        uspotUsers.setRole(Role.USER);

        return uspotUsers;
    }

}
