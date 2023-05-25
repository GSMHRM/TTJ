package com.example.todolist.zzzzzzzzzz;

import com.example.todolist.zzzzzzzzzz.request.CreatedRequest;
import com.example.todolist.zzzzzzzzzz.request.UpdatedCompletedRequest;
import com.example.todolist.zzzzzzzzzz.service.CreateTodoService;
import com.example.todolist.zzzzzzzzzz.service.DeleteTodoService;
import com.example.todolist.zzzzzzzzzz.service.ToDayListService;
import com.example.todolist.zzzzzzzzzz.service.UpdateCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class Control {


    private final CreateTodoService createTodoService;

    private final ToDayListService toDayListService;

    private final UpdateCompleteService completeService;

    private final DeleteTodoService deleteTodoService;

    @PostMapping("/create")
    public ResponseEntity<?> createToDo(@RequestBody CreatedRequest createdRequest) {
        createTodoService.execute(createdRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<?>> todayList() {
        List<ToDoList> todayList = toDayListService.TodayList();

        return new ResponseEntity<>(todayList, HttpStatus.OK);
    }

    @PatchMapping("/updateCompleted")
    public ResponseEntity<?> updateCompleted(@RequestParam Long id, @RequestBody UpdatedCompletedRequest completedRequest) {
        completeService.execute(id, completedRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestParam Long id) {
        deleteTodoService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
