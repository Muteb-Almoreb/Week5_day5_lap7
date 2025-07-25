package com.example.week5_day5_lap7_1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {



    @NotEmpty(message = "Lesson id must not be empty")
    private String id;

    @NotEmpty(message = "Lesson title must not be empty")
    private String title;

    @NotEmpty(message = "Lesson content must not be empty")
    private String content;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationInMinutes;
}
