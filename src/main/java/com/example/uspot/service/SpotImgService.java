package com.example.uspot.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SpotImgService {

    public void saveImg(Long num, List<MultipartFile> multipartFile) throws IOException;

    public void update(Long num, List<MultipartFile> multipartFile, Long mainino) throws IOException;

    public void removeimg(Long num);

}
