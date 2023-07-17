package com.example.demo3.Member.controller;

import com.example.demo3.Member.Service.MemberService;
import com.example.demo3.Member.domain.LoginRequest;
import com.example.demo3.Member.domain.Member;
import com.example.demo3.Member.domain.MemberRequest;
import com.example.demo3.Todos.domain.Todos;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity singUp(@RequestBody MemberRequest request){

        return memberService.save(request);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest req){
        return memberService.login(req);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Member> findAllByContent(
            @RequestParam(name="size",
                    required = false,
                    defaultValue = "10") int size,
            @RequestParam(name = "page",
                    required = false
                    ,defaultValue = "0") int page){
        return memberService.findAll(PageRequest.of(page,size));
    }

}
