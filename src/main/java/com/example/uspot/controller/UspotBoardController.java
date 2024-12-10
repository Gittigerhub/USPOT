package com.example.uspot.controller;

import com.example.uspot.dto.UspotBoardDTO;
import com.example.uspot.dto.UspotUsersDTO;
import com.example.uspot.entity.UspotBoard;
import com.example.uspot.entity.UspotUsers;
import com.example.uspot.repository.UspotBoardRepository;
import com.example.uspot.service.UspotBoardService;
import com.example.uspot.service.UspotUsersService;
import com.example.uspot.service.UspotUsersServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.zip.Inflater;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UspotBoardController {

    private final UspotUsersService uspotUsersService;

    private final UspotBoardService uspotBoardService;

    private final ModelMapper modelMapper;

    private final UspotBoardRepository uspotBoardRepository;

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

    @PostMapping("/sharepost")
    public String write(@Valid UspotBoardDTO uspotBoardDTO, BindingResult bindingResult,
                        List<MultipartFile> multipartFile , Model model) {

        // 들어오는값 확인
        log.info("post로 컨트롤러에 들어오는값 확인 : " + uspotBoardDTO);

        // 대표이미지 등록안학고 글작성시 리턴
        if(multipartFile.get(0).isEmpty()){
            model.addAttribute("msg", "대표이미지는 꼭 등록해주세요");
            return "uspot/board/write";
        }

        // 이미지 파일이 존재하는지 확인
        if(multipartFile != null){
            for (MultipartFile spot : multipartFile){
                if(!spot.getOriginalFilename().equals("")){
                    log.info(spot.getOriginalFilename());
                }
            }
        }

        // 유효성검사 진행
        if (bindingResult.hasErrors()){
            log.info("유효성검사 에러");
            log.info(bindingResult.getAllErrors());     // 확인된 모든 에러 콘솔창 출력


            return "uspot/board/write";                 // 다시 글작성 페이지
        }


        // 예외처리 진행
        try {

            // num값을 반환받아 read페이지로 이동하는데 사용
            Long savedBoardNum =
                    uspotBoardService.register(uspotBoardDTO, multipartFile);

            log.info("게시글등록 성공");

//            return  "redirect:/uspot/board/read?id=" + savedBoardNum;
            return  "redirect:/details";

        } catch (Exception e) {

            e.printStackTrace();
            log.info("파일등록간 문제가 발생했습니다.");
            model.addAttribute("msg" , "파일등록을 잘해주세요");
            return "uspot/board/write";        //다시 이전 페이지

        }

    }

    @GetMapping("/details")
    public String read(Long num, Model model, RedirectAttributes redirectAttributes) {

        // 예외처리
        try {
            UspotBoardDTO uspotBoardDTO =
                    uspotBoardService.read(num);

            model.addAttribute("uspotBoardDTO", uspotBoardDTO);

            return "uspot/board/read";

        } catch (EntityNotFoundException e) {

            redirectAttributes.addFlashAttribute("msg", "존재하지 않는 글입니다.");
            return "redirect:/uspot/board/list";
            //item/list?msg=존재하지

        }

    }


}
