package com.example.uspot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class fileService {

    // 물리적인 사진저장, 혹은 읽기등을 하려면 사진파일과 그로 만들어진 내용이 필요
    // 저장 경로
    @Value("${itemImgLocation}")
    String itemImgLocation;


    public String uploadFile(MultipartFile multipartFile) throws IOException {

        UUID uuid = UUID.randomUUID();  // 서로 다른개체를 구별하기 위해
                                        // 이름을 부여할 때 사용, 실제 사용시 중복될 가능성
                                        // 거의 없기 때문에

        String extension = multipartFile.getOriginalFilename()      // 오리지널 네임 가져오기
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                                                // "."을 기준으로 뒤에 내용만 잘라서 가지고있기 / 확장자 명

        // 물리적으로 저장될 파일이름 / afwaa3r513fd213(UUID).jpg
        String savedFileName = uuid.toString()+extension;

        // 저장 경로    /   C:/uspot/spot/afwaa3r513fd213(UUID).jpg
        String fileUploadFullUrl = itemImgLocation + "/" + savedFileName;

        //물리적인 저장   //다른방법으로는 multipartFile.transferTo(file);
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(multipartFile.getBytes());
        fos.close();
        // 처리되지 않은 예외처리 IOException는 호출로 넘긴다.


        return savedFileName;

    }

    // 물리적인 사진삭제
    public void removefile(String imgName){
        String delFileurl = itemImgLocation + "/" + imgName;
        System.out.println(delFileurl);

        File file = new File(delFileurl);

        if (file.exists()){     //파일존재여부확인
            file.delete();
        }
    }


}
