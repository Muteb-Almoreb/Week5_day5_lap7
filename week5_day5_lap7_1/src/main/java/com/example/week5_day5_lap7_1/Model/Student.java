package com.example.week5_day5_lap7_1.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @NotEmpty(message = "Id cant be empty")
    private String id;
    @NotEmpty(message = "Name Cant be Empty")
    private String name;
    @NotEmpty(message = " Email must be Not empty")
    @Email(message = "Enter Correct Email")
    private String email;

    @Min(value = 0, message = "GPA must be at least 0.0")
    @Max(value = 5, message = "GPA must be at most 5.0")
    private double GPA;


    private ArrayList<String> enrolledCourses =new ArrayList<>();

}
