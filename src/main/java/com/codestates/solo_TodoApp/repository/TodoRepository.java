package com.codestates.solo_TodoApp.repository;

import com.codestates.solo_TodoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    Optional<Todo> findByTodoOrder(int todo_Order);
}
