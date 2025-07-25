package com.example.week5_day5_lap7_1.Controller;


import com.example.week5_day5_lap7_1.API.ApiResponse;
import com.example.week5_day5_lap7_1.Model.Course;
import com.example.week5_day5_lap7_1.Model.Lesson;
import com.example.week5_day5_lap7_1.Services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {



    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllLessons() {
        ArrayList<Lesson> allLessons = new ArrayList<>();
        for (Course c : courseService.getAllCourses()) {
            allLessons.addAll(c.getLessons());
        }
        return ResponseEntity.ok(allLessons);
    }


    @GetMapping("/{lessonId}")
    public ResponseEntity<?> getLessonById(@PathVariable String lessonId) {
        for (Course c : courseService.getAllCourses()) {
            for (Lesson l : c.getLessons()) {
                if (l.getId().equalsIgnoreCase(lessonId)) {
                    return ResponseEntity.ok(l);
                }
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Lesson not found"));
    }

    // 3. Get lessons by title
    @GetMapping("/byTitle/{title}")
    public ResponseEntity<?> getLessonsByTitle(@PathVariable String title) {
        ArrayList<Lesson> result = new ArrayList<>();
        for (Course c : courseService.getAllCourses()) {
            for (Lesson l : c.getLessons()) {
                if (l.getTitle().equalsIgnoreCase(title)) {
                    result.add(l);
                }
            }
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/usedInCourses/{lessonId}")
    public ResponseEntity<?> getCoursesUsingLesson(@PathVariable String lessonId) {
        ArrayList<Course> result = new ArrayList<>();
        for (Course c : courseService.getAllCourses()) {
            for (Lesson l : c.getLessons()) {
                if (l.getId().equalsIgnoreCase(lessonId)) {
                    result.add(c);
                    break;
                }
            }
        }
        return ResponseEntity.ok(result);
    }
}
