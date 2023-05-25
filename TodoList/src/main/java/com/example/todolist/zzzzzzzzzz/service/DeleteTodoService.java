package com.example.todolist.zzzzzzzzzz.service;

import com.example.todolist.zzzzzzzzzz.ToDoList;
import com.example.todolist.zzzzzzzzzz.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTodoService {

    private final TodoRepository todoRepository;

    public void execute(Long id) {

        ToDoList toDoList = todoRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        todoRepository.deleteById(id);
    }
}
