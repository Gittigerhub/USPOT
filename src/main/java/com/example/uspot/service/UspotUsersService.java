package com.example.uspot.service;

import com.example.uspot.dto.UspotUsersDTO;
import com.example.uspot.entity.UspotUsers;

public interface UspotUsersService {

    // 회원가입시 동일 이메일, 주민번호로 가입된 계정이 있는지 확인
    public void validateDuplicateMember(String email, String regisnumber);

    // 회원가입 진행
    public UspotUsers register(UspotUsersDTO uspotUsersDTO);

}
