package com.example.todolist.zzzzzzzzzz;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<ToDoList, Long> {

}
