package com.example.uspot.service;

import com.example.uspot.dto.UspotBoardDTO;
import com.example.uspot.entity.UspotBoard;
import com.example.uspot.repository.UspotBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class UspotBoardServiceImpl implements UspotBoardService {

    private final UspotBoardRepository uspotBoardRepository;
    private final ModelMapper modelMapper;
    private final SpotImgService spotImgService;

    // 게시글 등록
    public Long register(UspotBoardDTO uspotBoardDTO, List<MultipartFile> multipartFiles) throws IOException {

        // 게시글dto를 엔티티로 변환
        UspotBoard uspotBoard = modelMapper.map(uspotBoardDTO, UspotBoard.class);

        // 엔티티내용 DB저장
        uspotBoard =
                uspotBoardRepository.save(uspotBoard);

        //이미지 등록
        spotImgService.saveImg(uspotBoard.getNum(), multipartFiles);

        // 게시글 엔티티 num으로 반환
        return uspotBoard.getNum();

    }

    // 추천글 상세페이지 보기
    public UspotBoardDTO read(Long num) {

        UspotBoard uspotBoard =
                uspotBoardRepository.findById(num).orElseThrow(EntityNotFoundException::new);

        UspotBoardDTO uspotBoardDTO = modelMapper.map(uspotBoard, UspotBoardDTO.class)
                .setSpotImgDTOList(uspotBoard.getSpotImgList());

        return uspotBoardDTO;
    }

}
