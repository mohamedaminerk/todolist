package com.medamine.todolist.Persistence;

import com.medamine.todolist.Model.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    boolean existsByTitleAndUser_Id(String title, Long userId);
    List<TaskEntity> findAllByUserId(Long userId);}
