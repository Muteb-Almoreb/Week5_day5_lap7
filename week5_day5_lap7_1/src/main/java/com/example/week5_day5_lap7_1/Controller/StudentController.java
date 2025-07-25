package com.example.week5_day5_lap7_1.Controller;


import com.example.week5_day5_lap7_1.API.ApiResponse;
import com.example.week5_day5_lap7_1.Model.Student;
import com.example.week5_day5_lap7_1.Services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        studentService.addStudent(student);
        return ResponseEntity.ok(new ApiResponse("Student added successfully"));
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    // 3. Update Student
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        boolean updated = studentService.updateStudent(id, student);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse("Student updated successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }

    // 4. Delete Student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse("Student deleted successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }



    // 5. Get student by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        Student s = studentService.getStudentByEmail(email);
        if (s != null) {
            return ResponseEntity.ok(s);
        }

        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }

    // 6. Get students with GPA >= value
    @GetMapping("/top/{gpa}")
    public ResponseEntity<?> getTopStudents(@PathVariable double gpa) {
        ArrayList<Student> top = studentService.getTopStudents(gpa);
        return ResponseEntity.ok(top);
    }

    // 7. Enroll in course
    @PutMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<?> enroll(@PathVariable String studentId, @PathVariable String courseId) {
        boolean enrolled = studentService.enrollInCourse(studentId, courseId);
        if (enrolled) {
            return ResponseEntity.ok(new ApiResponse("Student enrolled in course successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }

    // 8. Get student courses
    @GetMapping("/courses/{studentId}")
    public ResponseEntity<?> getCourses(@PathVariable String studentId) {
        ArrayList<String> courses = studentService.getCourses(studentId);
        if (courses != null) {
            return ResponseEntity.ok(courses);
        }

        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }
}
