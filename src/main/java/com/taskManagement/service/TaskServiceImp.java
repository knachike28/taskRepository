package com.taskManagement.service;

import com.taskManagement.dto.TaskRequestDTO;
import com.taskManagement.dto.TaskResponseDTO;
import com.taskManagement.entity.TaskManagement;
import com.taskManagement.enums.TaskStatus;
import com.taskManagement.repository.TaskManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService{

    private final TaskManagementRepository taskRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        TaskManagement taskManagement= new TaskManagement();
        taskManagement.setTitle(dto.getTitle());
        taskManagement.setDescription(dto.getDescription());
        taskManagement.setStatus(dto.getStatus());
        taskManagement.setPriority(dto.getPriority());
        taskManagement.setDueDate(dto.getDueDate());

        TaskManagement savedTask= taskRepository.save(taskManagement);

        return mapToResponseDTO(savedTask);
    }

    @Override
    public List<TaskResponseDTO> getAllTask() {
        List<TaskManagement> taskManagements= taskRepository.findAll();

        return taskManagements.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        TaskManagement taskManagement = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));

        return mapToResponseDTO(taskManagement);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        TaskManagement taskManagement = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));

        taskManagement.setTitle(dto.getTitle());
        taskManagement.setDescription(dto.getDescription());
        taskManagement.setStatus(dto.getStatus());
        taskManagement.setPriority(dto.getPriority());
        taskManagement.setDueDate(dto.getDueDate());

        TaskManagement updateTask= taskRepository.save(taskManagement);
        return mapToResponseDTO(updateTask);

    }

    @Override
    public void deleteTask(Long id) {
        TaskManagement taskManagement = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));
        taskRepository.delete(taskManagement);
    }

    @Override
    public List<TaskResponseDTO> getTasksByStatus(TaskStatus status) {
        List<TaskManagement> taskManagements= taskRepository.findByStatus(status);

        return taskManagements.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<TaskResponseDTO> getAllTasksWithPagination(Pageable pageable) {
        Page<TaskManagement> taskManagements= taskRepository.findAll(pageable);
        return taskManagements.map(this:: mapToResponseDTO);
    }

    // Convert Entity -> Response DTO
    private TaskResponseDTO mapToResponseDTO(TaskManagement taskManagement) {

        TaskResponseDTO dto= new TaskResponseDTO();
        dto.setId(taskManagement.getId());
        dto.setTitle(taskManagement.getTitle());
        dto.setDescription(taskManagement.getDescription());
        dto.setDueDate(taskManagement.getDueDate());
        dto.setPriority(taskManagement.getPriority());
        dto.setStatus(taskManagement.getStatus());
        return dto;
    }

}
