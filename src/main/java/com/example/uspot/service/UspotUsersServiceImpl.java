package com.example.uspot.service;

import com.example.uspot.dto.UspotUsersDTO;
import com.example.uspot.entity.UspotUsers;
import com.example.uspot.repository.UspotUsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class UspotUsersServiceImpl implements UspotUsersService, UserDetailsService {

    private final UspotUsersRepository uspotUsersRepository;

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UspotUsers uspotUsers =
                uspotUsersRepository.findByEmail(email);

        if (uspotUsers == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(uspotUsers.getName())
                .password(uspotUsers.getPassword())
                .roles(uspotUsers.getRole().name())
                .build();

    }

    // 중복가입 검사
    @Override
    public void validateDuplicateMember(String email, String regisnumber) {

        UspotUsers uspotUsers =
                uspotUsersRepository.findByEmail(email);

        UspotUsers uspotUsers1 =
                uspotUsersRepository.findByRegisnumber(regisnumber);

        if (email != null) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        } else if (regisnumber != null) {
            throw new IllegalStateException("이미 가입된 주민번호입니다.");
        } else if (email != null && regisnumber != null) {
            throw new IllegalStateException("이미 가입된 이메일과 주민번호입니다.");
        }


    }

    // 회원가입
    @Override
    public UspotUsers register(UspotUsersDTO uspotUsersDTO) {

        validateDuplicateMember(uspotUsersDTO.getEmail(), uspotUsersDTO.getRegisnumber());

        UspotUsers uspotUsers =
                uspotUsersDTO.dtoToEntity(uspotUsersDTO);

        uspotUsers =
                uspotUsersRepository.save(uspotUsers);

        return uspotUsers;
    }


}
