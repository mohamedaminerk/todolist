package com.medamine.todolist.Service;

import com.medamine.todolist.DTO.TaskDTO;
import com.medamine.todolist.Model.Exception.TaskCreationException;
import com.medamine.todolist.Model.Exception.TaskNotFoundException;
import com.medamine.todolist.Model.Entity.TaskEntity;
import com.medamine.todolist.Persistence.TaskRepository;
import com.medamine.todolist.Model.Exception.UserNotFoundException;
import com.medamine.todolist.Model.Entity.UserEntity;
import com.medamine.todolist.Persistence.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskEntity createTask(String description, String title, LocalDate date_deb, LocalDate date_fin, Long idUser) throws Exception {
        if (taskRepository.existsByTitleAndUser_Id(title, idUser)) {
            throw new TaskCreationException("A task with this title already exists for this user");
        }
        if (date_deb.isBefore(LocalDate.now())) {
            throw new TaskCreationException("The start date must be today or in the future!");
        }
        if (date_fin.isBefore(date_deb)) {
            throw new TaskCreationException("The end date must be after or equal to the start date!");
        }
        UserEntity user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        TaskEntity newTask = TaskEntity.builder()
                .description(description)
                .title(title)
                .startDate(date_deb)
                .endDate(date_fin)
                .status(false)
                .user(user)
                .build();
        taskRepository.save(newTask);
        return newTask;
    }

    public TaskDTO.PatchOutput updateTask(Long id, TaskDTO.PatchInput input) throws Exception {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found!"));

        if (input.getStartDate() != null && input.getEndDate() != null) {
            if (input.getEndDate().isBefore(input.getStartDate())) {
                throw new TaskCreationException("The end date must be after or equal to the start date!");
            }
        }

        if (task.getEndDate().isBefore(task.getStartDate())) {
            throw new TaskCreationException("The end date must be after or equal to the start date!");
        }
            task.setTitle(input.getTitle().trim());
            task.setDescription(input.getDescription().trim());
            task.setStatus(input.getStatus());
            task.setStartDate(input.getStartDate());
            task.setEndDate(input.getEndDate());
        taskRepository.save(task);
        return TaskDTO.PatchOutput.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .userId(task.getUser().getId())
                .taskId(task.getId())
                .build();

    }

    public void deleteTask (Long id){
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found!"));
        taskRepository.delete(task);
    }

    public List<TaskDTO.GetOutput> getTasks (Long userId) throws Exception {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        List<TaskEntity> tasks = taskRepository.findAllByUserId(userId);
        return tasks.stream()
                .map(task -> TaskDTO.GetOutput.builder()
                        .taskId(task.getId())
                        .status(task.getStatus())
                        .description(task.getDescription())
                        .title(task.getTitle())
                        .startDate(task.getStartDate())
                        .endDate(task.getEndDate())
                        .build()
                )
                .toList();
    }
}