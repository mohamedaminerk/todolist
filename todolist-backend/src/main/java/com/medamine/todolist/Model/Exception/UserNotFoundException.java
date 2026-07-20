package com.medamine.todolist.Model.Exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException (String message){
        super(message);
    }
}
