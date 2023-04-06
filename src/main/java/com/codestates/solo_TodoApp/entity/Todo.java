package com.codestates.solo_TodoApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, name = "todo_order")
    private int todoOrder;
    @Column(nullable = false)
    private boolean completed;
}
