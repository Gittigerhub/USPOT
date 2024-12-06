package com.example.uspot.service;

import com.example.uspot.dto.UspotUsersDTO;
import com.example.uspot.entity.UspotUsers;
import com.example.uspot.repository.UspotUsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;



    // 로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UspotUsers uspotUsers =
                uspotUsersRepository.findByEmail(email);

        if (uspotUsers == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(uspotUsers.getEmail())
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

        if (uspotUsers != null) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        } else if (uspotUsers1 != null) {
            throw new IllegalStateException("이미 가입된 주민번호입니다.");
        }


    }

    // 회원가입
    @Override
    public UspotUsers register(UspotUsersDTO uspotUsersDTO) {
        log.info("값이 들어오는지 확인 1" + uspotUsersDTO);
        validateDuplicateMember(uspotUsersDTO.getEmail(), uspotUsersDTO.getRegisnumber());

        log.info("값이 들어오는지 확인 2" + uspotUsersDTO);

        UspotUsers uspotUsers =
                uspotUsersDTO.dtoToEntity(uspotUsersDTO);

        uspotUsers =
                uspotUsersRepository.save(uspotUsers);

        return uspotUsers;
    }

    // 게시글 작성시 회원 이름 자동으로 등록
    public UspotUsers findByName(String email) {

        log.info("컨트롤러에서 서비스로 받은 email값 : " + email);

        UspotUsers uspotUsers =
            uspotUsersRepository.findByEmail(email);

        log.info("email로 찾은 uspotUsers값 : " + uspotUsers);

        return uspotUsers;

    }


}
