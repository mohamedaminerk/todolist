package com.medamine.todolist.Model.Exception;

public class UserCreationException extends RuntimeException{
    public UserCreationException(String message){
        super(message);
    }
}
