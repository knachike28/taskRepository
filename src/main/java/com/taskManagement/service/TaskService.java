package com.taskManagement.service;

import com.taskManagement.dto.TaskRequestDTO;
import com.taskManagement.dto.TaskResponseDTO;
import com.taskManagement.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO dto);

    List<TaskResponseDTO> getAllTask();

    TaskResponseDTO getTaskById(Long id);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO dto);

    void deleteTask(Long id);

    List<TaskResponseDTO> getTasksByStatus(TaskStatus status);

    Page<TaskResponseDTO> getAllTasksWithPagination(
            Pageable pageable);

}
