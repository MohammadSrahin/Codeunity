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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    private String name;
    private String authorId;
    private String description;
    private Date lastUpdated;

    @OneToMany
    private List<Lesson> Lessons = new ArrayList<>();

    @Override
    public String toString(){
        return name;
    }
}
