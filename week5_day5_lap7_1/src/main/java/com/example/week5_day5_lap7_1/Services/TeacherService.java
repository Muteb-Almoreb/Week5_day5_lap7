package com.example.week5_day5_lap7_1.Services;

import com.example.week5_day5_lap7_1.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    ArrayList<Teacher> teachers = new ArrayList<>();


    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }


    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }


    public boolean updateTeacher(String id, Teacher newTeacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)) {
                teachers.set(i, newTeacher);
                return true;
            }
        }
        return false;
    }


    public boolean deleteTeacher(String id) {

        for (Teacher t : teachers)
        {
            if(t.getId().equalsIgnoreCase(id))
            {
                teachers.remove(t);
                return true;
            }
        }

        return false;

    }

    public Teacher getTeacherByEmail(String email) {
        for (Teacher t : teachers) {
            if (t.getEmail().equalsIgnoreCase(email)) {
                return t;
            }
        }
        return null;
    }

    public Teacher getTeacherByCourse(String courseId)
    {
        for (Teacher t : teachers)
        {
            if(t.getCoursesTeaching().contains(courseId))
            {
                return t;
            }
        }
        return null;

    }



    public boolean assignCourse(String teacherId, String courseId) {
        for (Teacher t : teachers) {
            if (t.getId().equalsIgnoreCase(teacherId)) {
                t.getCoursesTeaching().add(courseId);
                return true;
            }
        }
        return false;
    }



        public ArrayList<String> getCoursesByTeacher(String teacherId) {
        for (Teacher t : teachers) {
            if (t.getId().equalsIgnoreCase(teacherId)) {
                return t.getCoursesTeaching();
            }
        }
        return null;
    }




}
