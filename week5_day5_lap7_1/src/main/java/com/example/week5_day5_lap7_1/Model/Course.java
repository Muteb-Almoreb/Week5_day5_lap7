package com.example.week5_day5_lap7_1.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {


    @NotEmpty(message = "Id cant be empty")
    private String id;
    @NotEmpty(message = "Name Cant be Empty")
    private String name;


    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 week")
    private Integer duration;

    private String teacherId;


    private ArrayList<String> studentIds = new ArrayList<>();

    private ArrayList<Lesson> lessons = new ArrayList<>();





}
