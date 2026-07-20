package com.medamine.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class TaskDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class PostInput{
        String title;
        String description;
        LocalDate startDate;
        LocalDate endDate;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class PatchInput {
        String title;
        String description;
        LocalDate startDate;
        LocalDate endDate;
        Boolean status;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class PostOutput{
        Long taskId;
        String title;
        String description;
        LocalDate startDate;
        LocalDate endDate;
        Boolean status;
        Long userId;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class PatchOutput{
        Long taskId;
        String title;
        String description;
        LocalDate startDate;
        LocalDate endDate;
        Boolean status;
        Long userId;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class GetOutput {
        Long taskId;
        String title;
        String description;
        LocalDate startDate;
        LocalDate endDate;
        Boolean status;
    }

    }
