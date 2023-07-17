package com.example.demo3.MemberLogin.Repository;

import com.example.demo3.Member.domain.Member;
import com.example.demo3.MemberLogin.domain.MemberLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLoginRepository extends JpaRepository<MemberLogin,Long> {

    MemberLogin findByMember_id(Long member_id);

}
