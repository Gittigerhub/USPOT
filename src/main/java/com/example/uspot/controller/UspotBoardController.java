package com.example.uspot.controller;

import com.example.uspot.dto.UspotUsersDTO;
import com.example.uspot.entity.UspotUsers;
import com.example.uspot.service.UspotUsersService;
import com.example.uspot.service.UspotUsersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UspotBoardController {

    private final UspotUsersService uspotUsersService;

    private final ModelMapper modelMapper;

    @GetMapping("/main")
    public String mainSearch() {

        return "uspot/board/mainsearch";
    }

    @GetMapping("/sharepost")
    public String write(Model model, Principal principal) {

        log.info("너 뭐야 principal : " + principal.getName());

        UspotUsers uspotUsers =
                uspotUsersService.findByName(principal.getName());

        log.info("컨트롤러에서 로그인한 유저의 이메일로 찾아온 uspotUsers : " + uspotUsers);

        UspotUsersDTO uspotUsersDTO =
            modelMapper.map(uspotUsers, UspotUsersDTO.class);

        log.info("컨트롤러에서 로그인한 유저의 이메일로 찾아온 DTO : " + uspotUsersDTO);

        model.addAttribute("uspotUsersDTO", uspotUsersDTO);

        return "uspot/board/write";
    }



}
