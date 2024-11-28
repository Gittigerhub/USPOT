package com.example.uspot.repository;

import com.example.uspot.entity.FollowList;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class MypageRepositoryTest {


    @Autowired
    private UspotUsersRepository uspotUsersRepository;
    @Autowired
    private FollowListRepository followListRepository;



    @Test
    public void friTest(){

        FollowList followList = new FollowList();
        followList.setMy(uspotUsersRepository.findById(1L).get());
        followList.setFri(uspotUsersRepository.findById(2L).get());

        followListRepository.save(followList);

        FollowList FollowList1 = new FollowList();
        FollowList1.setMy(uspotUsersRepository.findById(1L).get());
        FollowList1.setFri(uspotUsersRepository.findById(3L).get());

        followListRepository.save(FollowList1);

        FollowList FollowList2 = new FollowList();
        //FollowList2.setMy(나);
        //FollowList2.setFri("1번친구, 나쁜친구, 그런친구  ");
        // 문자열 자르기를 이용하여 하나하나 넣는식으로도 가능








    }

}