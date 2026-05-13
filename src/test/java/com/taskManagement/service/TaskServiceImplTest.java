package com.taskManagement.service;

import com.taskManagement.dto.TaskRequestDTO;
import com.taskManagement.entity.TaskManagement;
import com.taskManagement.enums.TaskPriority;
import com.taskManagement.enums.TaskStatus;
import com.taskManagement.repository.TaskManagementRepository;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskManagementRepository taskRepository;

    @InjectMocks
    private TaskServiceImp taskService;

    @Test
    void shouldCreateTaskSuccessfully() {

        TaskRequestDTO dto = new TaskRequestDTO();

        dto.setTitle("Learn Testing");
        dto.setDescription("Mockito Practice");
        dto.setStatus(TaskStatus.PENDING);
        dto.setPriority(TaskPriority.HIGH);
        dto.setDueDate(LocalDate.now());

        TaskManagement savedTask = new TaskManagement();

        savedTask.setId(1L);
        savedTask.setTitle(dto.getTitle());
        savedTask.setDescription(dto.getDescription());
        savedTask.setStatus(dto.getStatus());
        savedTask.setPriority(dto.getPriority());
        savedTask.setDueDate(dto.getDueDate());

        when(taskRepository.save(org.mockito.ArgumentMatchers.any(
                TaskManagement.class)))
                .thenReturn(savedTask);

        var response =
                taskService.createTask(dto);

        assertNotNull(response);

        assertEquals(
                "Learn Testing",
                response.getTitle());

        assertEquals(
                TaskStatus.PENDING,
                response.getStatus());
    }

    @Test
    void shouldGetTaskByIdSuccessfully() {

        TaskManagement task =
                new TaskManagement();

        task.setId(1L);
        task.setTitle("Learn Spring");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        var response =
                taskService.getTaskById(1L);

        assertNotNull(response);

        assertEquals(
                "Learn Spring",
                response.getTitle());
    }

    @Test
    void shouldUpdateTaskSuccessfully() {

        TaskManagement existingTask =
                new TaskManagement();

        existingTask.setId(1L);
        existingTask.setTitle("Old Task");

        TaskRequestDTO dto =
                new TaskRequestDTO();

        dto.setTitle("Updated Task");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(existingTask));

        when(taskRepository.save(existingTask))
                .thenReturn(existingTask);

        var response =
                taskService.updateTask(1L, dto);

        assertEquals(
                "Updated Task",
                response.getTitle());
    }

    @Test
    void shouldDeleteTaskSuccessfully() {

        TaskManagement task =
                new TaskManagement();

        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository)
                .delete(task);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {

        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,

                        () -> taskService.getTaskById(1L));

        assertEquals(
                "Task not found",
                exception.getMessage());
    }

//    @Test
//    void shouldReturnBadRequestWhenTitleMissing()
//            throws Exception {
//
//        String requestBody = """
//        {
//            "status": "PENDING",
//            "priority": "HIGH"
//        }
//        """;
//
//        mockMvc.perform(post("/taskManagement")
//                        .contentType("application/json")
//                        .content(requestBody))
//
//                .andExpect(status().isBadRequest());
//    }

}