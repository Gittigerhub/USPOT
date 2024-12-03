package com.example.uspot.controller;

import com.example.uspot.dto.UspotUsersDTO;
import com.example.uspot.service.UspotUsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UspotUsersController {

    private final UspotUsersService uspotUsersService;

    // 로그인
    @GetMapping("/login")
    public String userLogin() {

        return "uspot/user/loginForm";
    }

    // 회원가입
    @GetMapping("/register")
    public String userRegister(Model model) {

        model.addAttribute("uspotUsersDTO", new UspotUsersDTO());

        return "uspot/user/registerForm";
    }

    @PostMapping("/register")
    public String userRegister(@Valid UspotUsersDTO uspotUsersDTO, BindingResult bindingResult, Model model) {

        // 들어온 값 확인
        log.info("컨트롤러로 들어온 uspotUsersDTO 값 : " + uspotUsersDTO);

        // 유효성검사
        if (bindingResult.hasErrors()) {
            log.info("유효성 문제 발견");
            StringBuffer sb = new StringBuffer();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {

                log.info("필드 : " + fieldError.getField() + ", 메세지 : " + fieldError.getDefaultMessage());
                sb.append(fieldError.getDefaultMessage());

            }

            log.info(sb.toString());
            return "uspot/user/registerForm";

        }

        // 유효성 검사 통과시 회원가입 진행
        try {

            uspotUsersService.register(uspotUsersDTO);

        }catch (IllegalStateException e) {

            return "uspot/user/registerForm";

        }

        // 회원가입 완료시 로그인 페이지로 이동
        return "redirect:/uspot/user/loginForm";
    }


}
