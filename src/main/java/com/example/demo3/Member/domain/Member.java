package com.example.demo3.Member.domain;

import com.example.demo3.Like.domain.Like;
import com.example.demo3.Todos.domain.Todos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    private Integer age;


    @JsonIgnoreProperties("member")
    @OneToMany
    private List<Todos> todosList = new ArrayList<>();

//    @OneToMany
//    private List<Like> likes = new ArrayList<>();
//

}
