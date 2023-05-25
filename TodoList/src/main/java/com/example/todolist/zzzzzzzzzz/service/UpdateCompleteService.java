package com.example.todolist.zzzzzzzzzz.service;

import com.example.todolist.zzzzzzzzzz.ToDoList;
import com.example.todolist.zzzzzzzzzz.TodoRepository;
import com.example.todolist.zzzzzzzzzz.request.UpdatedCompletedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCompleteService {

    private final TodoRepository todoRepository;

    public void execute(Long id, UpdatedCompletedRequest completedRequest) {
        ToDoList toDoList = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물"));

        toDoList.setCompleted(completedRequest.getCompleted());

        todoRepository.save(toDoList);
    }
}
