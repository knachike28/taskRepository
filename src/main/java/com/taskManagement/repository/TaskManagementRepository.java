package com.taskManagement.repository;

import com.taskManagement.entity.TaskManagement;
import com.taskManagement.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskManagementRepository extends JpaRepository <TaskManagement, Long> {

    List<TaskManagement> findByStatus(
            TaskStatus status);
}
