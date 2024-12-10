package com.example.uspot.repository;

import com.example.uspot.entity.SpotImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotImgRepository extends JpaRepository<SpotImg, Long> {

    public List<SpotImg> findByNum (Long num);

    public SpotImg findByNumAndRepimgYn (Long num, String val);


}
