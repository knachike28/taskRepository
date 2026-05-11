package com.taskManagement.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    private Long id;
    private String title;
    private String Description;
    private String status;
    private LocalDate dueDate;
    private String priority;
}
