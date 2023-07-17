package com.example.demo3.Member.util;

import com.example.demo3.MemberLogin.Repository.MemberLoginRepository;
import com.example.demo3.MemberLogin.domain.MemberLogin;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@AllArgsConstructor
public class TimeThread {

    private MemberLoginRepository memberLoginRepository;

    public void create(MemberLogin memberLogin){


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            memberLoginRepository.deleteById(memberLogin.getMember_login_id());
        }
    };

    // 1000 = 1초
    long delay= 1000L*20 ;

    Timer timer = new Timer();

    timer.schedule(task,delay);
}

}
