package com.example.todolist.zzzzzzzzzz.service;

import com.example.todolist.zzzzzzzzzz.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetTodoByDay {

    private final TodoRepository todoRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
