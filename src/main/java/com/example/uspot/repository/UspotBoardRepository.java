package com.example.uspot.repository;

import com.example.uspot.entity.SpotImg;
import com.example.uspot.entity.UspotBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UspotBoardRepository extends JpaRepository<UspotBoard, Long> {



}
