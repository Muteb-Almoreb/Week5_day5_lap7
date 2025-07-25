package com.example.week5_day5_lap7_1.Services;


import com.example.week5_day5_lap7_1.Model.Lesson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LessonService {

    ArrayList<Lesson> lessons = new ArrayList<>();


    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }


    public ArrayList<Lesson> getAllLessons() {
        return lessons;
    }


    public boolean updateLesson(String id, Lesson newLesson) {
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getId().equalsIgnoreCase(id)) {
                lessons.set(i, newLesson);
                return true;
            }
        }
        return false;
    }


    public boolean deleteLesson(String id) {
        for (Lesson l : lessons) {
            if (l.getId().equalsIgnoreCase(id)) {
                lessons.remove(l);
                return true;
            }
        }
        return false;
    }


    public Lesson getLessonByTitle(String title) {
        for (Lesson l : lessons) {
            if (l.getTitle().equalsIgnoreCase(title)) {
                return l;
            }
        }
        return null;
    }


    public ArrayList<Lesson> getLessonsByMinDuration(int minDuration) {
        ArrayList<Lesson> result = new ArrayList<>();
        for (Lesson l : lessons) {
            if (l.getDurationInMinutes() >= minDuration) {
                result.add(l);
            }
        }
        return result;
    }


    public ArrayList<Lesson> searchLessonsByKeyword(String keyword) {
        ArrayList<Lesson> result = new ArrayList<>();
        for (Lesson l : lessons) {
            if (l.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(l);
            }
        }
        return result;
    }


    public ArrayList<Lesson> getLessonsByExactDuration(int duration) {
        ArrayList<Lesson> result = new ArrayList<>();
        for (Lesson l : lessons) {
            if (l.getDurationInMinutes() == duration) {
                result.add(l);
            }
        }
        return result;
    }
}

