package com.example.week5_day5_lap7_1.Services;

import com.example.week5_day5_lap7_1.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public ArrayList<Student> getAllStudents()
    {
        return students;
    }

    public boolean updateStudent(String id , Student newStudent)
    {
        for (int i =0;i<students.size();i++)
        {
            if(students.get(i).getId().equalsIgnoreCase(id))
            {
                students.set(i ,newStudent);
                return true;
            }
        }

        return false;

    }


    public boolean deleteStudent(String id){

        for (Student s : students  )
        {
            if(s.getId().equalsIgnoreCase(id))
            {
                students.remove(s);
                return true;
            }
        }

        return false;
    }

    public Student getStudentByEmail(String email){

        for (Student s : students)
        {
            if(s.getEmail().equalsIgnoreCase(email))
            {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Student> getTopStudents(double mGPA)
    {
        ArrayList<Student> topStudent= new ArrayList<>();
        for(Student s : students)
        {
            if(s.getGPA()>=mGPA)
            {
                topStudent.add(s);

            }
        }
        return topStudent;
    }

    public boolean enrollInCourse(String id , String courseId)
    {
        for (Student s : students)
        {
            if(s.getId().equalsIgnoreCase(id))
            {
                s.getEnrolledCourses().add(courseId);
                return true;
            }
        }
        return false;
    }


    public ArrayList<String> getCourses(String id)
    {

        for (Student s : students)
        {
            if(s.getId().equalsIgnoreCase(id))
            {
               return s.getEnrolledCourses();
            }

        }
        return null;
    }



}
