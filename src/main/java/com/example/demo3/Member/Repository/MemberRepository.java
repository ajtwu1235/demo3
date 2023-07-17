package com.example.demo3.Member.Repository;

import com.example.demo3.Like.domain.Like;
import com.example.demo3.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

    Member findByEmailAndPassword(String email,String password);
}
