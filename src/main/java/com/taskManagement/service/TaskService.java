package com.taskManagement.service;

import com.taskManagement.dto.TaskRequestDTO;
import com.taskManagement.dto.TaskResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO dto);

    List<TaskResponseDTO> getAllTask();

    TaskResponseDTO getTaskById(Long id);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO dto);

    void deleteTask(Long id);
}
