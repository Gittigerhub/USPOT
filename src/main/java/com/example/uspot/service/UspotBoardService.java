package com.example.uspot.service;

import com.example.uspot.dto.UspotBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UspotBoardService {

    // 글 등록
    public Long register(UspotBoardDTO uspotBoardDTO, List<MultipartFile> multipartFiles) throws IOException;

    // 상세 페이지
    public UspotBoardDTO read(Long num);

}
