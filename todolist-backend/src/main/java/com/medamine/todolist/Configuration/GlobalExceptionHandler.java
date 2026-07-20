package com.medamine.todolist.Configuration;

import com.medamine.todolist.Model.Exception.TaskCreationException;
import com.medamine.todolist.Model.Exception.TaskNotFoundException;
import com.medamine.todolist.Model.Exception.UserCreationException;
import com.medamine.todolist.Model.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> catchAny(Exception ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pd);
    }
    @ExceptionHandler(TaskCreationException.class)
    public ResponseEntity<ProblemDetail> catchTaskCreation(TaskCreationException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ProblemDetail> catchTaskNotFound(TaskNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }
    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ProblemDetail> catchUserCreation(UserCreationException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> catchUserNotFound(UserNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }
}
