package com.taskManagement.entity;

import com.taskManagement.enums.TaskPriority;
import com.taskManagement.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "taskManagement")
public class TaskManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String Description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
