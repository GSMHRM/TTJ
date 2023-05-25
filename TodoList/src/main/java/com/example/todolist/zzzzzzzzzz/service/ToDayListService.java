package com.example.todolist.zzzzzzzzzz.service;

import com.example.todolist.zzzzzzzzzz.ToDoList;
import com.example.todolist.zzzzzzzzzz.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDayListService {

    private final TodoRepository todoRepository;

    public List<ToDoList> TodayList() {
        return todoRepository.findAll();
    }
}
