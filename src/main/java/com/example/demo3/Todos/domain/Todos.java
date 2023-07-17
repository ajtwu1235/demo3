package com.example.demo3.Todos.domain;

import com.example.demo3.Like.domain.Like;
import com.example.demo3.Member.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todos {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private Boolean isDone;

    //초기값 설정
    private int likeCount=0;

    @JsonIgnoreProperties("todosList")
    @ManyToOne
    private Member member;

//    @OneToMany
//    private List<Like> likes = new ArrayList<>();
//


}
