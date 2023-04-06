package com.codestates.solo_TodoApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter@Setter
public class TodoPatchDto {
    private Long todoId;
    private String title;
    @Positive(message = "todo_order는 1 이상이어야 합니다.")
    private Integer order;
    private Boolean completed;
}
