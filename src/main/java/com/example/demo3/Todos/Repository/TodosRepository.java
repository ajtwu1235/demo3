package com.example.demo3.Todos.Repository;

import com.example.demo3.Todos.domain.Todos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todos,Long> {


    Page<Todos> findByContentContainsAndIsDone(String content, Pageable pageable,Boolean isDone);

    Page<Todos> findByTitleContains(String Title, Pageable pageable);


}