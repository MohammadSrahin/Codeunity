package edu.just.codeunity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String title;
    private String body;
    private String foot;

    @OneToOne
    private Exam exam;

    @Override
    public String toString(){
        return title;
    }
}
