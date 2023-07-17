package com.example.demo3.Member.Service;

import com.example.demo3.Member.Repository.MemberRepository;
import com.example.demo3.Member.domain.LoginRequest;
import com.example.demo3.Member.domain.Member;
import com.example.demo3.Member.domain.MemberRequest;
import com.example.demo3.Member.util.TimeThread;
import com.example.demo3.MemberLogin.Repository.MemberLoginRepository;
import com.example.demo3.MemberLogin.domain.MemberLogin;
import com.example.demo3.Todos.domain.Todos;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberLoginRepository memberLoginRepository;

    public ResponseEntity save(MemberRequest request) {

        Member member = Member.builder().email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .age(request.getAge())
                .build();


        if(memberRepository.findByEmail(member.getEmail())==null){
            memberRepository.save(member);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("EXISTS EMAIL",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity login (LoginRequest req){

        Member member = memberRepository.findByEmailAndPassword(req.getEmail(), req.getPassword());

        if(member==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            // 유사 세션
            MemberLogin memberLogin = new MemberLogin(null, member, LocalDateTime.now(),LocalDateTime.now().plusMinutes(10L));
            memberLoginRepository.save(memberLogin);
            // 종료스레드를 셋팅
            TimeThread thread = new TimeThread(memberLoginRepository);
            thread.create(memberLogin);
            return new ResponseEntity(HttpStatus.OK);
        }

    }

    public Page<Member> findAll(PageRequest of) {

        return memberRepository.findAll(of);
    }
}
