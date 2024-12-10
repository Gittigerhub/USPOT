package com.example.uspot.service;

import com.example.uspot.entity.SpotImg;
import com.example.uspot.entity.UspotBoard;
import com.example.uspot.repository.SpotImgRepository;
import com.example.uspot.repository.UspotBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class SpotImgServiceImpl implements SpotImgService {

    private final SpotImgRepository spotImgRepository;
    private final fileService fileService;
    private final UspotBoardRepository uspotBoardRepository;


    // 이미지 등록
    // 게시글PK에 이미지파일의 확장자를 따로 잘라 경로를 붙여 저장한다.
    public void saveImg(Long num, List<MultipartFile> multipartFile) throws IOException {

        log.info("스팟이미지 서비스로 들어온 num : " + num);

        // 이미지 파일이 있다면, 이미지파일 리스트에서 하나하나 가져와, 그 이미지가 안 비어있있다면
        // 물리적 저장과 DB저장 각각진행하고 DB저장시에는 대표이미지 여부 또한 확인한다.

        if (multipartFile != null) {
            for (MultipartFile spot : multipartFile) {
                if (!spot.isEmpty()){
                    log.info("스팟이미지 서비스로 들어온 이미지 : " + spot.getOriginalFilename());

                    // 물리적 저장
                    String savedFileName = // UUID가 포함된 물리적인 파일이름
                            // 물리적 저장을 위한 fileService 생성
                            fileService.uploadFile(spot);   //  컨트롤러로 예외처리 넘김


                    // DB에 저장
                    // 엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다.
                    UspotBoard uspotBoard =
                            uspotBoardRepository.findById(num).get();

                    String imgPath = "/img/spot/" + savedFileName;

                    SpotImg spotImg = new SpotImg();
                    spotImg.setUspotBoard(uspotBoard);                      //본문 // 이미지가 달릴 게시글
                    spotImg.setImgName(savedFileName);                      //uuid포함 저장될 이름
                    spotImg.setImgPath(imgPath);                            // 이미지 조회 경로
                    spotImg.setOriginalName(spot.getOriginalFilename());    // 원래이름

                    //대표이미지 여부 확인
                    if(multipartFile.indexOf(spot) == 0){
                        spotImg.setRepimgYn("Y");      //대표이미지
                    }else {
                        spotImg.setRepimgYn("N");
                    }
                    spotImgRepository.save(spotImg);

                }

            }
        }


    }

    public void update(Long num, List<MultipartFile> multipartFile, Long mainino) throws IOException {
        // mainino는 update시 view단에서 받아 올 대표이미지 name값

        log.info("스팟이미지 서비스로 들어온 num : " + num);

        // 이미지 파일이 있다면, 이미지파일 리스트에서 하나하나 가져와, 그 이미지가 안 비어있있다면
        // 물리적 저장과 DB저장 각각진행하고 DB저장시에는 대표이미지 여부 또한 확인한다.

        if (multipartFile != null) {
            for (MultipartFile spot : multipartFile) {
                if (!spot.isEmpty()){
                    log.info("스팟이미지 서비스로 들어온 이미지 : " + spot.getOriginalFilename());

                    // 물리적 저장
                    String savedFileName = // UUID가 포함된 물리적인 파일이름
                            // 물리적 저장을 위한 fileService 생성
                            fileService.uploadFile(spot);   //  컨트롤러로 예외처리 넘김


                    // DB에 저장
                    // 엔티티를 가져왔다면 중복코드를 사용할 필요가 없어진다.
                    UspotBoard uspotBoard =
                            uspotBoardRepository.findById(num).get();

                    String imgPath = "/img/spot/" + savedFileName;

                    SpotImg spotImg = new SpotImg();
                    spotImg.setUspotBoard(uspotBoard);                      //본문 // 이미지가 달릴 게시글
                    spotImg.setImgName(savedFileName);                      //uuid포함 저장될 이름
                    spotImg.setImgPath(imgPath);                            // 이미지 조회 경로
                    spotImg.setOriginalName(spot.getOriginalFilename());    // 원래이름

                    //대표이미지 여부 확인
                    if (mainino == null) {
                        if(multipartFile.indexOf(spot) == 0){

                            spotImg =
                                    spotImgRepository.findByNumAndRepimgYn(num, "Y");
                            spotImg.setUspotBoard(uspotBoard);                      //본문 // 이미지가 달릴 아이템
                            spotImg.setImgName(savedFileName);                      //uuid포함 저장될 이름
                            spotImg.setImgPath(imgPath);                            //경로
                            spotImg.setOriginalName(spot.getOriginalFilename());    // 원래이름

                        }else {
                            spotImg.setRepimgYn("N");
                        }

                    }else {
                        spotImg.setRepimgYn("N");
                    }

                    spotImgRepository.save(spotImg);

                }

            }
        }

    }

    public void removeimg(Long num){
        // 물리적 파일을 삭제하기 위해서 데이터를 가져온다.
        SpotImg spotImg =
                spotImgRepository.findById(num).get();

        // FileService안에 있는 저장경로 Value 이용

        // 물리적 파일삭제
        // 삭제하려면 경로 및 사진파일명 필요
        fileService.removefile(spotImg.getImgName());
        // 반환값에 따라 물리파일이 삭제가 되었다면, 안되었다면에 따라
        // db를 지우거나, 지우지 않거나 진행

        //db에서 값을 지운다.
        spotImgRepository.deleteById(num);

    }



}
