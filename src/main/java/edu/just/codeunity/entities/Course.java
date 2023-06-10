package edu.just.codeunity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    private String authorId;
    private String description;
    private Date lastUpdated;

    @OneToMany
    private List<Lesson> Lessons = new ArrayList<>();

    @OneToOne
    private Exam exam;

    public void updateCourse(Course course) {
        this.authorId = course.authorId;
        this.description = course.description;
        this.name = course.name;

        setLessons(course.getLessons());
        setExam(course.getExam());
        lastUpdated = new Date();
    }

    @Override
    public String toString(){
        return name;
    }
}
