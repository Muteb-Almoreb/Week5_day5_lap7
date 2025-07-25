package com.example.week5_day5_lap7_1.Services;

import com.example.week5_day5_lap7_1.Model.Course;
import com.example.week5_day5_lap7_1.Model.Lesson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {


    ArrayList<Course> courses = new ArrayList<>();


    public void addCourse(Course course) {
        courses.add(course);
    }


        public ArrayList<Course> getAllCourses() {
            return courses;
        }

    public boolean updateCourse(String id, Course newCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equalsIgnoreCase(id)) {
                courses.set(i, newCourse);
                return true;
            }
        }
        return false;
    }


    public boolean deleteCourse(String id) {
        for (Course c : courses) {
            if (c.getId().equalsIgnoreCase(id)) {
                courses.remove(c);
                return true;
            }
        }
        return false;
    }



    public Course getCourseByName(String name) {
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }


    public ArrayList<Course> getCoursesByDuration(int minDuration) {
        ArrayList<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getDuration() >= minDuration) {
                result.add(c);
            }
        }
        return result;
    }


    public boolean enrollStudent(String courseId, String studentId) {
        for (Course c : courses) {
            if (c.getId().equalsIgnoreCase(courseId)) {
                c.getStudentIds().add(studentId);
                return true;
            }
        }
        return false;
    }


    public boolean addLesson(String courseId, Lesson lesson) {
        for (Course c : courses) {
            if (c.getId().equalsIgnoreCase(courseId)) {
                c.getLessons().add(lesson);
                return true;
            }
        }
        return false;
    }

}


