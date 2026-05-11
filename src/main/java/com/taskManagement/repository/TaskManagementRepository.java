package com.taskManagement.repository;

import com.taskManagement.entity.TaskManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagementRepository extends JpaRepository <TaskManagement, Long> {
}
