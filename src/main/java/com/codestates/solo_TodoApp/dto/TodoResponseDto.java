package com.codestates.solo_TodoApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class TodoResponseDto {
    private long todoId;
    private String title;
    private int order;
    private boolean completed;
}
