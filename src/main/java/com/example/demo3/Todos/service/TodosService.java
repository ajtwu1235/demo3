package com.example.demo3.Todos.service;

import com.example.demo3.Like.Repository.LikeRepository;
import com.example.demo3.Like.domain.Like;
import com.example.demo3.Member.Repository.MemberRepository;
import com.example.demo3.Member.domain.Member;
import com.example.demo3.MemberLogin.Repository.MemberLoginRepository;
import com.example.demo3.MemberLogin.domain.MemberLogin;
import com.example.demo3.Todos.Repository.TodosRepository;
import com.example.demo3.Todos.domain.Todos;
import com.example.demo3.Todos.domain.TodosRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodosService {

    private final TodosRepository todosRepository;
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final MemberLoginRepository memberLoginRepository;
    public Page<Todos> findByContentContains(String keyword, PageRequest of, Boolean isDone) {

        return todosRepository.findByContentContainsAndIsDone(keyword,of,isDone);
    }

    public Page<Todos> findByNameContains(String keyword, PageRequest of) {

        return todosRepository.findByTitleContains(keyword,of);
    }

    // 기능 추가
    public ResponseEntity save(TodosRequest request) {

        MemberLogin byMemberId = memberLoginRepository.findByMember_id(request.getMemberId());

        if(byMemberId==null||byMemberId.getEndAt().isBefore( LocalDateTime.now())){

           return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Member member = memberRepository.findById(request.getMemberId()).get();

        Todos todos = Todos.builder().title(request.getTitle())
                .content(request.getContent())
                .isDone(request.getIsDone())
                .member(member)
                .build();
        //양방향
        member.getTodosList().add(todos);


        todosRepository.save(todos);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public Todos update(Todos todos) {

        Optional<Todos> byId = todosRepository.findById(todos.getId());
        return byId.orElseThrow(()->new RuntimeException("TODOS_NOT_FOUND"));


    }

    public void deleteById(Long id) {

         todosRepository.deleteById(id);
    }

    public Like saveLike(Long todoId, Long memberId){

        Todos todos = todosRepository.findById(todoId).orElseThrow(() -> new RuntimeException("TODOS_NOT_FOUND"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("MEMBERS_NOT_FOUND"));

        Like like = new Like(null,member,todos);

        return likeRepository.save(like);
    }


    public Like check(Long todoId, Long memberId) {

        Todos todos = todosRepository.findById(todoId).orElseThrow(() -> new RuntimeException("TODOS_NOT_FOUND"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("MEMBERS_NOT_FOUND"));

        todos.setIsDone(true);


        Like like = likeRepository.findByMemberAndTodos(member, todos);


        if(like==null){
            return likeRepository.save(new Like(null,member,todos));
        }

        return like;

    }
}
