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
        if(verifyExistTodo(todo.getTodoOrder())) {
            todo.setTodoId(repository.findByTodoOrder(todo.getTodoOrder()).get().getTodoId());
            return updateTodo(todo);
        }
        else return repository.save(todo);
//        return verifyExistTodo(todo.getTodoOrder()) ? updateTodo(todo) : repository.save(todo);
//        return repository.save(todo);
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

    //---------------------------------------------

    // private boolean verifyExistTodo(int todoOrder) 방식
    private boolean verifyExistTodo(int todoOrder) {
        Optional<Todo> optionalTodo = repository.findByTodoOrder(todoOrder);
        return optionalTodo.isPresent(); //Todo : 예외의 인자 완성하기!
    }
    // verifyExistTodo(Todo todo) 방식
//    private boolean verifyExistTodo(Todo todo) {
//        Optional<Todo> optionalTodo = repository.findByTodoOrder(todo.getTodoOrder());
//        boolean result = optionalTodo.isPresent(); //Todo : 예외의 인자 완성하기!
//        if(result == true) todo.setTodoId(optionalTodo.get().getTodoId());
//        return result;
//    }
    private Todo findVerifiedTodo(long todoId) {
        Optional<Todo> optionalTodo = repository.findById(todoId);
        Todo todo = optionalTodo.orElseThrow(()->new RuntimeException());  //Todo : 예외의 인자 완성하기!
        return todo;
    }
}
