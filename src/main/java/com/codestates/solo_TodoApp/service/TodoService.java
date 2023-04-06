package com.codestates.solo_TodoApp.service;

import com.codestates.solo_TodoApp.entity.Todo;
import com.codestates.solo_TodoApp.exception.BusinessLogicException;
import com.codestates.solo_TodoApp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    public final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo createTodo(Todo todo){
//        verifyExistTodo(todo.getTodoOrder());


        return repository.save(todo);
    }
    public Todo updateTodo(Todo todo){
        Todo findTodo = findVerifiedTodo(todo.getTodoId());

        Optional.ofNullable(todo.getTodoOrder()).ifPresent(todo_order -> findTodo.setTodoOrder(todo_order));
        Optional.ofNullable(todo.getTitle()).ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.isCompleted()).ifPresent(completed -> findTodo.setCompleted(completed));

        return repository.save(findTodo);
    }
    public Todo findTodo(long todoId){
        return findVerifiedTodo(todoId);
    }
    public List<Todo> findTodos(){
        return repository.findAll();
    }
    public void deleteTodo(long todoId){
        repository.delete(findVerifiedTodo(todoId));
    }

    /////////////////////////////////////////////////////
    private void verifyExistTodo(int todo_order) {
        Optional<Todo> optionalTodo = repository.findByTodoOrder(todo_order);
        if(optionalTodo.isPresent()) throw new BusinessLogicException(); //Todo : 예외의 인자 완성하기!
    }
    private Todo findVerifiedTodo(long todoId) {
        Optional<Todo> optionalTodo = repository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(()->new RuntimeException());  //Todo : 예외의 인자 완성하기!
        return todo;
    }
}
