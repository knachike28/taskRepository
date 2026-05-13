package com.taskManagement.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String Description;
    @NotBlank(message = "Status is required")
    private String status;
    private LocalDate dueDate;
    @NotBlank(message = "Priority is required")
    private String priority;
}
