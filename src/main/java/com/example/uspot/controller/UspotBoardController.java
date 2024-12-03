package com.example.uspot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UspotBoardController {

    @GetMapping("/main")
    public String mainSearch() {

        return "uspot/board/mainsearch";
    }

    @GetMapping("/sharepost")
    public String write() {

        return "uspot/board/write";
    }



}
