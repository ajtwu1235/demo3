package com.example.demo3.Like.domain;

import com.example.demo3.Member.domain.Member;
import com.example.demo3.Todos.domain.Todos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_like")
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Todos todos;



}
