package com.example.uspot.repository;

import com.example.uspot.entity.FollowList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<FollowList, Long> {


}
