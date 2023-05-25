package com.example.todolist.zzzzzzzzzz.service;

import com.example.todolist.zzzzzzzzzz.request.CreatedRequest;
import com.example.todolist.zzzzzzzzzz.ToDoList;
import com.example.todolist.zzzzzzzzzz.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateTodoService {


    private final TodoRepository todoRepository;

    public void execute(CreatedRequest createdRequest) {

        ToDoList toDoList = ToDoList.builder()
                .content(createdRequest.getContent())
                .completed(false)
                .createdDate(LocalDateTime.now())
                .build();

        todoRepository.save(toDoList);
    }
}
