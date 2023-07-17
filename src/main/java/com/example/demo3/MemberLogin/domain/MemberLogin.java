package com.example.demo3.MemberLogin.domain;

import com.example.demo3.Member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLogin {

    @Id
    @GeneratedValue
    private Long member_login_id;

    @OneToOne
    private Member member;

    private LocalDateTime createdAt;

    private LocalDateTime endAt;


//    @OneToMany
//    private List<Like> likes = new ArrayList<>();
//

}
