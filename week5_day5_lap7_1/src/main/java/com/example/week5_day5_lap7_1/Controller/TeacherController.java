package com.example.week5_day5_lap7_1.Controller;


import com.example.week5_day5_lap7_1.API.ApiResponse;
import com.example.week5_day5_lap7_1.Model.Teacher;
import com.example.week5_day5_lap7_1.Services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {


    private final TeacherService teacherService;


    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        teacherService.addTeacher(teacher);
        return ResponseEntity.ok(new ApiResponse("Teacher added successfully"));
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @Valid @RequestBody Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        boolean updated = teacherService.updateTeacher(id, teacher);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse("Teacher updated successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id) {
        boolean deleted = teacherService.deleteTeacher(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse("Teacher deleted successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        Teacher t = teacherService.getTeacherByEmail(email);
        if (t != null) {
            return ResponseEntity.ok(t);
        }

        return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
    }


    @GetMapping("/byCourse/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable String courseId) {
        Teacher t = teacherService.getTeacherByCourse(courseId);
        if (t != null) {
            return ResponseEntity.ok(t);
        }

        return ResponseEntity.status(404).body(new ApiResponse("Teacher not found for this course"));
    }


    @PutMapping("/assign/{teacherId}/{courseId}")
    public ResponseEntity<?> assignCourse(@PathVariable String teacherId, @PathVariable String courseId) {
        boolean assigned = teacherService.assignCourse(teacherId, courseId);
        if (assigned) {
            return ResponseEntity.ok(new ApiResponse("Course assigned to teacher successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
    }


    @GetMapping("/courses/{teacherId}")
    public ResponseEntity<?> getCourses(@PathVariable String teacherId) {
        ArrayList<String> courses = teacherService.getCoursesByTeacher(teacherId);
        if (courses != null) {
            return ResponseEntity.ok(courses);
        }

        return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
    }
}

