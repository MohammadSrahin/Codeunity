package edu.just.codeunity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String title;
    private String body;
    private String foot;
    private String language;

    @OneToOne
    private Exam exam;

    @Override
    public String toString(){
        return title;
    }

    public void updateLesson(Lesson lesson) {
        this.title = lesson.getTitle();
        this.body = lesson.getBody();
        this.foot = lesson.getFoot();
        this.language = lesson.getLanguage();
        this.exam = lesson.getExam();
    }
}
