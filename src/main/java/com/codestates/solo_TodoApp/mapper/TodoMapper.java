package com.codestates.solo_TodoApp.mapper;

import com.codestates.solo_TodoApp.dto.TodoPatchDto;
import com.codestates.solo_TodoApp.dto.TodoPostDto;
import com.codestates.solo_TodoApp.dto.TodoResponseDto;
import com.codestates.solo_TodoApp.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    @Mapping(source = "order",target = "todoOrder")
    Todo TodoPostDtoToTodo(TodoPostDto postDto);
    @Mapping(source = "order",target = "todoOrder")
    Todo TodoPatchDtoToTodo(TodoPatchDto patchDto);
    @Mapping(source = "todoOrder",target = "order")
    TodoResponseDto ToDoToTodoResponseDto(Todo todo);
    @Mapping(source = "todoOrder",target = "order")
    List<TodoResponseDto> TodosToTodoResponseDtos(List<Todo> todos);
}
