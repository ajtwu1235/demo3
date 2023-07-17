package com.example.demo3.Member.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequest {
    private String email;
    private String password;
    private String name;
    private Integer age;
}
