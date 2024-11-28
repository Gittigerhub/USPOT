package com.example.uspot.repository;

import com.example.uspot.entity.UspotUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UspotUsersRepository extends JpaRepository<UspotUsers, Long> {

    UspotUsers findByEmail(String email);

    UspotUsers findByRegisnumber(String regisnumber);



}
