package com.taskManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "taskManagement")
public class TaskManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;
    private String Description;

    @NotBlank(message = "Status is required")
    private String status;

    private LocalDate dueDate;

    @NotBlank(message = "Priority is required")
    private String priority;
}
