package com.example.demo3.Like.Repository;

import com.example.demo3.Like.domain.Like;
import com.example.demo3.Member.domain.Member;
import com.example.demo3.Todos.domain.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    Like findByMemberAndTodos(Member member, Todos todos);
}
