package com.medamine.todolist.Controllers;

import com.medamine.todolist.DTO.TaskDTO;
import com.medamine.todolist.Model.Entity.TaskEntity;
import com.medamine.todolist.Model.Entity.UserEntity;
import com.medamine.todolist.Persistence.UserRepository;
import com.medamine.todolist.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks")
@RestController
public class TaskRestController {
    private final TaskService taskService;
    private final UserRepository userRepository;

    public TaskRestController(TaskService taskService, UserRepository userRepository){
        this.taskService = taskService;
        this.userRepository = userRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDTO.PostOutput post(@Valid @RequestBody TaskDTO.PostInput input,
                                   Authentication authentication) throws Exception{
        String username = authentication.getName();
        UserEntity currentUser = userRepository.findByUsername(username);

        TaskEntity output = taskService.createTask(
                input.getDescription(), input.getTitle(),
                input.getStartDate(), input.getEndDate(),
                currentUser.getId()
        );
        return TaskDTO.PostOutput.builder()
                .taskId(output.getId())
                .title(output.getTitle())
                .description(output.getDescription())
                .startDate(output.getStartDate())
                .endDate(output.getEndDate())
                .userId(output.getUser().getId())
                .status(output.getStatus())
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}")
    public TaskDTO.PatchOutput update(@PathVariable Long id,
                                      @Valid @RequestBody TaskDTO.PatchInput patchInput) throws Exception{
        return taskService.updateTask(id, patchInput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<TaskDTO.GetOutput> get(Authentication authentication) throws Exception{
        String username = authentication.getName();
        UserEntity currentUser = userRepository.findByUsername(username);
        return taskService.getTasks(currentUser.getId());
    }
}