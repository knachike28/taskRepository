package com.taskManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String Description;
    private String status;
    private LocalDate dueDate;
    private String priority;
}
