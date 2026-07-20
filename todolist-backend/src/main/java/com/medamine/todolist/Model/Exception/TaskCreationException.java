package com.medamine.todolist.Model.Exception;

public class TaskCreationException extends RuntimeException{
    public TaskCreationException(String message){
        super(message);
    }
}
