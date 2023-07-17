package com.example.demo3.Todos.controller;


import com.example.demo3.Like.domain.Like;
import com.example.demo3.Todos.domain.Todos;
import com.example.demo3.Todos.domain.TodosRequest;
import com.example.demo3.Todos.service.TodosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService todosService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Todos> findAllByContent(
            @RequestParam(name="size",
                    required = false,
                    defaultValue = "20") int size,
            @RequestParam(name = "page",
                    required = false
                    ,defaultValue = "0") int page,
            @RequestParam(name = "keyword",
                    required = false,
                    defaultValue = "") String keyword,
            @RequestParam(name = "isDone",
                    required = false,
                    defaultValue = "false") Boolean isDone){
        return todosService.findByContentContains(keyword, PageRequest.of(page,size),isDone);
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/title")
    public Page<Todos> findAllByTitle(
            @RequestParam(name="size",
                    required = false,
                    defaultValue = "20") int size,
            @RequestParam(name = "page",
                    required = false
                    ,defaultValue = "0") int page,
            @RequestParam(name = "keyword",
                    required = false,
                    defaultValue = "") String keyword){
        return todosService.findByNameContains(keyword, PageRequest.of(page,size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveTodos(@RequestBody TodosRequest request){

        return todosService.save(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todos updateTodos(@RequestBody Todos todos){

        return todosService.update(todos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTodos(@PathVariable Long id){
        todosService.deleteById(id);
    }

    @PostMapping("{todoId}/like/{memberId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Like saveLike(@PathVariable Long todoId,
                         @PathVariable Long memberId){

       return todosService.saveLike(todoId,memberId);
    }

    @PutMapping("{todoId}/like/{memberId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Like updateTodos(@PathVariable Long todoId,
                             @PathVariable Long memberId){

        return todosService.check(todoId,memberId);
    }



}
