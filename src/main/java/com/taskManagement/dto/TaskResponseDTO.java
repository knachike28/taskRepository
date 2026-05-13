package com.taskManagement.dto;

import com.taskManagement.enums.TaskPriority;
import com.taskManagement.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String Description;
    private TaskStatus status;
    private LocalDate dueDate;
    private TaskPriority priority;
}
