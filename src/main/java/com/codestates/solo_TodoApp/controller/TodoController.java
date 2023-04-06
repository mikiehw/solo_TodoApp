package com.codestates.solo_TodoApp.controller;

import com.codestates.solo_TodoApp.dto.TodoPatchDto;
import com.codestates.solo_TodoApp.dto.TodoPostDto;
import com.codestates.solo_TodoApp.entity.Todo;
import com.codestates.solo_TodoApp.mapper.TodoMapper;
import com.codestates.solo_TodoApp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/")
@Validated  // @PathVariable에 유효성 검증 있을 경우에 사용해야함.
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoPostDto postDto) {
        Todo todo = todoService.createTodo(mapper.TodoPostDtoToTodo(postDto));

        URI uri = UriComponentsBuilder.newInstance()
                .path("/" + todo.getTodoId())
                .buildAndExpand().toUri();

        return ResponseEntity.created(uri).build();  // post는 리스폰스 객체를 넘기지 않고, 저장된 리소스의 uri 정보를 보내준다.
    }
    @PostMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive Long todoId,
                                    @Valid @RequestBody TodoPatchDto patchDto) {
        patchDto.setTodoId(todoId);
        Todo todo = todoService.updateTodo(mapper.TodoPatchDtoToTodo(patchDto));

        return new ResponseEntity<>(mapper.ToDoToTodoResponseDto(todo), HttpStatus.OK);
    }
    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive Long todoId) {
        Todo todo = todoService.findTodo(todoId);

        return new ResponseEntity<>(mapper.ToDoToTodoResponseDto(todo),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();

        return new ResponseEntity<>(mapper.TodosToTodoResponseDtos(todos), HttpStatus.OK);
    }
    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive Long todoId) {
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
