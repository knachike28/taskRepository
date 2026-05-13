package com.taskManagement.controller;

import com.taskManagement.dto.TaskRequestDTO;
import com.taskManagement.dto.TaskResponseDTO;
import com.taskManagement.enums.TaskPriority;
import com.taskManagement.enums.TaskStatus;
import com.taskManagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/taskManagement")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto){
        TaskResponseDTO response= taskService.createTask(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<TaskResponseDTO>> getAllTask(){
        List<TaskResponseDTO> tasks= taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/{id}")
    public ResponseEntity <TaskResponseDTO> getTaskById(@PathVariable Long id){
        TaskResponseDTO task= taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto){
        TaskResponseDTO updateResponse= taskService.updateTask(id, dto);
        return ResponseEntity.ok(updateResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task Deleted successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTO>> searchTaskByStatus(@PathVariable TaskStatus status){
        List<TaskResponseDTO> tasks= taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }
}
