package com.codestates.solo_TodoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Getter
public class TodoPostDto {
    @NotBlank(message = "제목을 작성해야 합니다.")
    private String title;
//    @Positive(message = "todo_order는 1 이상이어야 합니다.")
    private Integer order;
//    @NotBlank(message = "완료여부를 기입해야합니다.(true / false)")
    private Boolean completed;
}
