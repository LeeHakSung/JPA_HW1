package com.alvin.jpastudy.service;

import com.alvin.jpastudy.entity.Member;
import com.alvin.jpastudy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Alvin (playgo@coupang.com)
 * Date: 2015. 10. 31.
 * Time: 오후 2:23
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void saveMember() {
        Member firstMember = new Member("이학성", 35);

        firstMember.setId("Alvin");

        memberRepository.save(firstMember);
    }
}
