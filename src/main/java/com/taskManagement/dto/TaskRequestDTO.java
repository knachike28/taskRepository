package com.taskManagement.dto;


import com.taskManagement.enums.TaskPriority;
import com.taskManagement.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String Description;
    @NotNull(message = "Status is required")
    private TaskStatus status;
    private LocalDate dueDate;
    @NotNull(message = "Priority is required")
    private TaskPriority priority;
}
