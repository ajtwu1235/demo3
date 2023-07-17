package com.example.demo3.Todos.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodosRequest {

    private String title;
    private String content;
    private Boolean isDone;
    private Long memberId;
}
