package com.alvin.jpastudy.repository;

import com.alvin.jpastudy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Alvin (playgo@coupang.com)
 * Date: 2015. 10. 31.
 * Time: 오후 2:16
 */
public interface MemberRepository extends JpaRepository<Member, String> {
}
