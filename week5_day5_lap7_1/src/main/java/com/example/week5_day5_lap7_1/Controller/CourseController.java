package com.example.week5_day5_lap7_1.Controller;


import com.example.week5_day5_lap7_1.API.ApiResponse;
import com.example.week5_day5_lap7_1.Model.Course;
import com.example.week5_day5_lap7_1.Model.Lesson;
import com.example.week5_day5_lap7_1.Services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor

public class CourseController {


    private final CourseService courseService;


    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        courseService.addCourse(course);
        return ResponseEntity.ok(new ApiResponse("Course added successfully"));
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        boolean updated = courseService.updateCourse(id, course);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse("Course updated successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse("Course deleted successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Course c = courseService.getCourseByName(name);
        if (c != null) {
            return ResponseEntity.ok(c);
        }

        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }


    @GetMapping("/duration/{min}")
    public ResponseEntity<?> getByDuration(@PathVariable int min) {
        ArrayList<Course> result = courseService.getCoursesByDuration(min);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/enroll/{courseId}/{studentId}")
    public ResponseEntity<?> enrollStudent(@PathVariable String courseId, @PathVariable String studentId) {
        boolean enrolled = courseService.enrollStudent(courseId, studentId);
        if (enrolled) {
            return ResponseEntity.ok(new ApiResponse("Student enrolled in course successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }


    @PutMapping("/addLesson/{courseId}")
    public ResponseEntity<?> addLesson(@PathVariable String courseId, @RequestBody Lesson lesson) {
        boolean added = courseService.addLesson(courseId, lesson);
        if (added) {
            return ResponseEntity.ok(new ApiResponse("Lesson added to course"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }
}
